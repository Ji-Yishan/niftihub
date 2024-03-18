package com.example.niftihub.ws.pojo;

public enum MessageError {

    ERROR,//不知道什么错误，但是就是错了
    SYSTEM_ERROR,//无法判断是否为系统信息
    TIME_NULL,//时间为空
    FROMUID_NULL,//发送者uid为空
    MESSAGE_NULL,//消息为空
    PRIVATE_ERROR,//无法判断是否为私聊
    TARGETUID_NULL,//目标uid为空
    TRUE,//正确，该类型不会返回客户端

}
