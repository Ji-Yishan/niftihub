package com.example.niftihub.ws;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.niftihub.component.RabbitMQSend;
import com.example.niftihub.config.WebSocketGetHttpSessionConfig;
import com.example.niftihub.pojo.data.MessageDO;
import com.example.niftihub.service.impl.GroupServiceImpl;
import com.example.niftihub.service.impl.MessageServiceImpl;
import com.example.niftihub.ws.pojo.MessageError;
import com.example.niftihub.ws.pojo.OnlineUsers;
import com.example.niftihub.ws.pojo.SystemMessageType;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@Component
@ServerEndpoint(value = "/chat",configurator = WebSocketGetHttpSessionConfig.class)
@Slf4j
public class ChatEndpoint {
    private HttpSession httpSession;
    private String userUid;
    private static RabbitMQSend rabbitMQSend;
    private static MessageServiceImpl messageService;
    private static GroupServiceImpl groupService;

    @Autowired
    public void setRabbitMQSend(RabbitMQSend rabbitMQSend){
        ChatEndpoint.rabbitMQSend = rabbitMQSend;
    }
    @Autowired
    public void setMessageService(MessageServiceImpl messageService){
        ChatEndpoint.messageService = messageService;
    }
    @Autowired
    public void setGroupService(GroupServiceImpl groupService){
        ChatEndpoint.groupService = groupService;
    }


    //ws连接，需要把用户放到在线用户中，然后读取数据库，将未发送的消息进行发送
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.userUid = (String) this.httpSession.getAttribute("uid");
        if(userUid == null){
            log.info("userId == null");
            userUid = session.getId();
        }
        OnlineUsers.addUser(userUid,session);

        JSONObject message = JSONUtil.createObj()
                .putOnce("type",SystemMessageType.USER_ONLINE.toString())
                .putOnce("data", null);
        JSONObject systemMessage = JSONUtil.createObj()
                .putOnce("system",true)
                .putOnce("message",message.toString())
                .putOnce("targetUid",userUid)
                .putOnce("fromUid",userUid)
                .putOnce("time",DateUtil.now());
        //发送用户上线消息
        rabbitMQSend.send(systemMessage.toString());
    }
    //关闭连接，需要将用户从在线用户中删除
    @OnClose
    public void onClose(Session session){
        log.info(userUid+"关闭连接");
        OnlineUsers.remove(userUid);
    }

    //收到客户端消息
    @OnMessage
    public void onMessage(String message) throws IOException {
        log.info("客户端发送消息");
        MessageError messageError = verifyTheData(message,userUid);
        if(messageError == MessageError.TRUE) {
            //交给消息队列完成
            rabbitMQSend.send(message);
        }else {
            JSONObject jsonObject = JSONUtil.createObj()
                            .putOnce("system",true)
                            .putOnce("message",messageError.toString())
                            .putOnce("time",DateUtil.now());
            OnlineUsers.getSession(this.userUid).getBasicRemote().sendText(jsonObject.toString());
        }
    }
    //异常
    @OnError
    public void onError(Session session, Throwable exception) throws Exception{
        log.error("出现异常");
        log.error(exception.toString());
        JSONObject jsonObject = JSONUtil.createObj()
                .putOnce("system",true)
                .putOnce("message",exception.toString())
                .putOnce("time",DateUtil.now());
        OnlineUsers.getSession(this.userUid).getBasicRemote().sendText(jsonObject.toString());
        if(session.isOpen()){
            session.close();
        }
        //去除用户
        OnlineUsers.remove(session.getId());
    }
//    //用来测试连接的
//    @Scheduled(fixedRate = 2000)
//    public void sendMessage() throws IOException {
//        for(String key: OnlineUsers.getKeySet()){
//            OnlineUsers.getSession(key).getBasicRemote().sendText("心跳");
//        }
//    }

    public static void sendMessage(MessageDO messageDO,String targetUid) throws IOException {

        Session session = OnlineUsers.getSession(targetUid);
        if(session != null){
            session.getBasicRemote().sendText(JSONUtil.toJsonStr(messageDO));
        }

    }
    public static void sendGroupMessage(MessageDO messageDO) throws IOException {
        //读取数据库，获取成员信息，如果在在线就发送消息（调用上面的函数）
        Set<Object> groupMembers = groupService.getGroupMembers(messageDO.getTargetUid());
        for(Object object:groupMembers){
            sendMessage(messageDO,object.toString());
        }
    }
    //本来应该和sendMessage一起的，但是当时没有写好，不想改了
    public static void sendMessage(JSONObject jsonObject,String targetUID) throws IOException {
        Session session = OnlineUsers.getSession(targetUID);
        if(session != null){
            session.getBasicRemote().sendText(jsonObject.toString());
        }
    }

    //按道理来说，还要检验targetUID是否正确，但是，~不想写~
    public static MessageError verifyTheData(String message,String userUid){
        JSONObject messageJson = JSONUtil.parseObj(message);
        if(messageJson.get("time") == null){
            return MessageError.TIME_NULL;
        }
        if(!Objects.equals(messageJson.get("fromUid"),userUid)){
            return MessageError.FROMUID_ERROR;
        }
        if(messageJson.get("targetUid") == null){
            return MessageError.TARGETUID_NULL;
        }
        boolean system = (boolean) messageJson.get("system");
        if(system){
            log.info("系统消息");
            //系统消息验证
            JSONObject systemMessageJson = new JSONObject(messageJson.get("message"));
            try{
                SystemMessageType.valueOf((String) systemMessageJson.get("type"));
            }catch (IllegalArgumentException illegalArgumentException){
                return MessageError.ERROR;
            }
        }else{
            log.info("非系统消息");
            //验证非系统消息
            if(messageJson.get("message") == null){
                return MessageError.MESSAGE_NULL;
            }
            if(! (Objects.equals(messageJson.get("ifPrivate"),true) ||
                    Objects.equals(messageJson.get("ifPrivate"),false))){
                return MessageError.PRIVATE_ERROR;
            }
        }
        return MessageError.TRUE;
    }

}
