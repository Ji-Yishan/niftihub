package com.example.niftihub.service.impl;

import com.example.niftihub.dao.MessageMapper;
import com.example.niftihub.pojo.data.MessageDO;
import com.example.niftihub.service.inter.MessageService;
import org.checkerframework.checker.guieffect.qual.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;


    @Override
    public int addMessage(MessageDO messageDO) {
        return messageMapper.addMessage(messageDO);
    }

    @Override
    public MessageDO selectMessage(String UID) {
        return messageMapper.selectMessage(UID);
    }
}
