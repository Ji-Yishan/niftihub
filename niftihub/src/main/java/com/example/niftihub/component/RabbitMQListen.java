package com.example.niftihub.component;

import cn.hutool.json.JSONUtil;
import com.example.niftihub.pojo.data.MessageDO;
import com.example.niftihub.service.impl.MessageServiceImpl;
import com.example.niftihub.ws.ChatEndpoint;
import com.example.niftihub.ws.pojo.WebsocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RabbitMQListen {
    @Autowired
    MessageServiceImpl messageService;
    @RabbitListener(queues = "message")
    public void listen(String msg){
        log.info("接收到："+msg);
        WebsocketMessage websocketMessage = JSONUtil.toBean(msg, WebsocketMessage.class);
        if(websocketMessage.isSystem()){
            handingSystemMessage(websocketMessage);
        }else{
            handingSystemMessage(websocketMessage);
        }
    }

    public void handingMessage(WebsocketMessage websocketMessage) throws IOException {
        MessageDO messageDO = new MessageDO(websocketMessage);
        //往数据库中存
        messageService.addMessage(messageDO);
        //todo 然后给目标发送消息
        if(messageDO.isIfPrivate()){
            ChatEndpoint.sendPrivateMessage(messageDO);
        }else {
            ChatEndpoint.sendGroupMessage(messageDO);
        }
    }
    public void handingSystemMessage(WebsocketMessage websocketMessage){

    }
}
