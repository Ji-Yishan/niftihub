package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.MessageDO;

import java.util.Set;

public interface MessageService {
    int addMessage(MessageDO messageDO);
    MessageDO selectMessage(String UID);
    long setUnreadMessage(String UID,String messageUID);
    Set<Object> getUnreadMessage(String UID);
    long setUnreadRemove(String UID,String messageUID);
}
