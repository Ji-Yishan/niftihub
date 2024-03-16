package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Isabella
 * @create: 2023-09-26 23:49
 **/
@Mapper
public interface UserMapper {
    UserDO selectUserInfo(int uid);
    int addUser(UserDO userDO);
    int updateUserInfo(UserDO userDO);
    int deleteUser(int uid);
}
