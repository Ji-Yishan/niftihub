package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.AdminDO;
import com.example.niftihub.pojo.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    AdminDTO login(String username, String password);//返回uid
    Integer selectLevel(String uid);
    Integer selectUsername(String username);
    int creatAdminUser(AdminDO adminDO);
}
