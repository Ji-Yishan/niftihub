package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.MessageDO;

public interface MessageService {
    int addMessage(MessageDO messageDO);
    MessageDO selectMessage(String UID);
}
