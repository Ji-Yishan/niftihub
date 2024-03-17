package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.MessageDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    int addMessage(MessageDO messageDO);
}
