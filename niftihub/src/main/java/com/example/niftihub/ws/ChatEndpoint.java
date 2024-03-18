package com.example.niftihub.ws;


import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.niftihub.component.RabbitMQSend;
import com.example.niftihub.config.WebSocketGetHttpSessionConfig;
import com.example.niftihub.controller.UserController;
import com.example.niftihub.pojo.data.MessageDO;
import com.example.niftihub.service.impl.MessageServiceImpl;
import com.example.niftihub.uitl.UUID;
import com.example.niftihub.ws.pojo.MessageError;
import com.example.niftihub.ws.pojo.OnlineUsers;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import java.io.IOException;
import java.util.Objects;

@Component
@ServerEndpoint(value = "/chat",configurator = WebSocketGetHttpSessionConfig.class)
@Slf4j
public class ChatEndpoint {
    private HttpSession httpSession;
    private String userUid;
    private static RabbitMQSend rabbitMQSend;
    private static MessageServiceImpl messageService;

    @Autowired
    public void setRabbitMQSend(RabbitMQSend rabbitMQSend){
        ChatEndpoint.rabbitMQSend = rabbitMQSend;
    }
    @Autowired
    public void setMessageService(MessageServiceImpl messageService){
        ChatEndpoint.messageService = messageService;
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
        //System.out.println(messageService.selectMessage("99c26817612b460eb1b0eefc1093ed09"));

        //todo 接下来是数据库操作
        //读取redis，查看未读消息，发送消息
//        MessageDO messageDO = new MessageDO(UUID.getUUID(),"1","消息",true,"1", DateUtil.now());
//        rabbitMQSend.send(JSONUtil.toJsonStr(messageDO));

    }
    //关闭连接，需要将用户从在线用户中删除
    @OnClose
    public void onClose(Session session){
        log.info(userUid+"关闭连接");
        OnlineUsers.remove(userUid);
    }

    //发送消息，如果对方不在线，则只保存在数据库，不发送
    @OnMessage
    public void onMessage(String message) throws IOException {
        //todo 对数据进行校验 校验失败则返回系统消息
        MessageError messageError = verifyTheData(message);
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
        if(session.isOpen()){
            session.close();
        }
        //去除用户
        OnlineUsers.remove(session.getId());
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() throws IOException {
        for(String key: OnlineUsers.getKeySet()){
            OnlineUsers.getSession(key).getBasicRemote().sendText("心跳");
        }
    }

    public static void sendPrivateMessage(MessageDO messageDO) throws IOException {

        String userUid = messageDO.getFromUid();

        Session session = OnlineUsers.getSession(userUid);
        if(session != null){
            session.getBasicRemote().sendText(JSONUtil.toJsonStr(messageDO));
        }
        //todo 往redis中放未读消息（为保护服务器，过期时间设置为1周）

    }

    public static void sendGroupMessage(MessageDO messageDO){

        //读取数据库，获取成员信息，然后发送消息（调用上面的函数）



    }

    public static MessageError verifyTheData(String message){
        JSONObject jsonMessage = JSONUtil.parseObj(message);
        if(jsonMessage.get("time") == null){
            return MessageError.TIME_NULL;
        }
        String system = (String) jsonMessage.get("system");
        if(Objects.equals(system,"true")){
            //系统消息验证
        }else if(Objects.equals(system,"false")){
            //验证非系统消息
            if(jsonMessage.get("fromUid") == null){
                return MessageError.FROMUID_NULL;
            }
            if(jsonMessage.get("message") == null){
                return MessageError.MESSAGE_NULL;
            }
            if(! (Objects.equals(jsonMessage.get("ifPrivate"),"true") ||
                    Objects.equals(jsonMessage.get("ifPrivate"),"false"))){
                return MessageError.PRIVATE_ERROR;
            }
            if(jsonMessage.get("targetUid") == null){
                return MessageError.TARGETUID_NULL;
            }
        }else{
            return MessageError.SYSTEM_ERROR;
        }



        return MessageError.TRUE;
    }


}
