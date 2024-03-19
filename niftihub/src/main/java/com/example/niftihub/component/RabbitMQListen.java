package com.example.niftihub.component;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.niftihub.pojo.data.MessageDO;
import com.example.niftihub.service.impl.GroupServiceImpl;
import com.example.niftihub.service.impl.MessageServiceImpl;
import com.example.niftihub.service.inter.MessageService;
import com.example.niftihub.ws.ChatEndpoint;
import com.example.niftihub.ws.pojo.SystemMessageType;
import com.example.niftihub.ws.pojo.WebsocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
public class RabbitMQListen {
    @Autowired
    MessageServiceImpl messageService;
    @Autowired
    GroupServiceImpl groupService;
    @RabbitListener(queues = "message")
    public void listen(String msg) throws IOException {
        log.info("接收到消息队列消息");
        WebsocketMessage websocketMessage = JSONUtil.toBean(msg, WebsocketMessage.class);
        //todo 取消注释
        if(websocketMessage.isSystem()){
            log.info("系统消息");
            handingSystemMessage(websocketMessage);
        }else{
            log.info("非系统消息");
            handingMessage(websocketMessage);
        }
    }

    public void handingMessage(WebsocketMessage websocketMessage) throws IOException {
        MessageDO messageDO = new MessageDO(websocketMessage);
        //往数据库中存
        messageService.addMessage(messageDO);
        log.info("往mysql存消息："+messageDO.getUID());
        //然后给目标发送消息
        if(messageDO.isIfPrivate()){
            ChatEndpoint.sendMessage(messageDO,messageDO.getTargetUid());
            //todo 设置过期时间
            messageService.setUnreadMessage(messageDO.getTargetUid(),messageDO.getUID());
        }else {
            ChatEndpoint.sendGroupMessage(messageDO);
            Set<Object> groupMembers = groupService.getGroupMembers(messageDO.getTargetUid());
            for(Object object:groupMembers){
                messageService.setUnreadMessage(object.toString(),messageDO.getUID());
            }
        }
    }
    public void handingSystemMessage(WebsocketMessage websocketMessage){
        //todo 判断系统消息类型，进行相应操作
        String message = JSONUtil.parseObj(websocketMessage).get("message").toString();
        SystemMessageType systemMessageType =
                JSONUtil.parseObj(message).get("type", SystemMessageType.class);
        if(systemMessageType == SystemMessageType.READ_MESSAGE){
            messageService.setUnreadRemove(websocketMessage.getFromUid(), websocketMessage.getTargetUid());
            log.info("已读某个消息");
        }else if(systemMessageType == SystemMessageType.JOIN_GROUP){
            groupService.joinGroup(websocketMessage.getTargetUid(), websocketMessage.getFromUid());
        }else if(systemMessageType == SystemMessageType.QUIT_GROUP){
            groupService.exitGroup(websocketMessage.getTargetUid(), websocketMessage.getFromUid());
        } else if(systemMessageType == SystemMessageType.USER_ONLINE){
            Set<Object> unreadMessage =
                    messageService.getUnreadMessage(websocketMessage.getFromUid());
            for(Object object:unreadMessage){
                //todo 查询mysql获得消息
            }

        }else {
            log.error("错误的系统消息");
        }

    }

















}
