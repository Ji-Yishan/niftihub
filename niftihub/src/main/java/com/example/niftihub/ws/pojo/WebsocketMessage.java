package com.example.niftihub.ws.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WebsocketMessage {
    private boolean system;//是否为系统消息
    private String UID;//这条消息在数据库中的uid，可能为空
    private String fromUid;//发送者uid
    private String message;//发送的消息，如果是系统消息，则为系统的消息
    private boolean ifPrivate;//是否为私聊，true为私聊，false为群聊消息
    private String targetUid;//发送给某个用户或者群的uid
    private String time;//时间,2024-03-17 16:47:12


}
