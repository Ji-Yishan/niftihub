package com.example.niftihub.service.impl;

import com.example.niftihub.dao.MessageMapper;
import com.example.niftihub.pojo.data.MessageDO;
import com.example.niftihub.service.inter.MessageService;
import com.example.niftihub.uitl.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    RedisUtil redisUtil;


    @Override
    public int addMessage(MessageDO messageDO) {
        return messageMapper.addMessage(messageDO);
    }

    @Override
    public MessageDO selectMessage(String UID) {
        return messageMapper.selectMessage(UID);
    }

    @Override
    public long setUnreadMessage(String UID, String messageUID) {
        return redisUtil.sSet("nifthub-unread-"+UID,messageUID);
    }

    @Override
    public Set<Object> getUnreadMessage(String UID) {
        return redisUtil.sGet("nifthub-unread-"+UID);
    }

    @Override
    public long setUnreadRemove(String UID, String messageUID) {
        return redisUtil.setRemove("nifthub-unread-"+UID,messageUID);
    }
}
