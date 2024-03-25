package com.example.niftihub.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    String login(String username,String password);//返回uid
    Integer selectLevel(String uid);

}
