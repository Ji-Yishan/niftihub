package com.example.niftihub.pojo.data;

import com.example.niftihub.uitl.UUID;
import com.example.niftihub.ws.pojo.WebsocketMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDO {
    private String UID;//消息uid
    private String fromUid;//发送者uid
    private String message;//发送的消息，如果是系统消息，则为系统的消息
    private boolean ifPrivate;//是否为私聊，true为私聊，false为群聊消息
    private String targetUid;//发送给某个用户或者群的uid
    private String time;//发送时间

    public MessageDO(WebsocketMessage websocketMessage) {
        this.UID = UUID.getUUID();
        this.fromUid = websocketMessage.getFromUid();
        this.message = websocketMessage.getMessage();
        this.ifPrivate = websocketMessage.isIfPrivate();
        this.targetUid = websocketMessage.getTargetUid();
        this.time = websocketMessage.getTime();
    }

    public static MessageDO newMessage(WebsocketMessage websocketMessage){
        MessageDO messageDO = new MessageDO();
        messageDO.setUID(UUID.getUUID());
        messageDO.setFromUid(websocketMessage.getFromUid());
        messageDO.setMessage(websocketMessage.getMessage());
        messageDO.setIfPrivate(websocketMessage.isIfPrivate());
        messageDO.setTargetUid(websocketMessage.getTargetUid());
        messageDO.setTime(websocketMessage.getTime());
        return messageDO;
    }

}
