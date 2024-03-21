package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.UserDO;

/**
 * @author: Isabella
 * @create: 2024-03-16 19:33
 **/
public interface  UserService {
    int login(UserDO userDO);
    UserDO selectUserInfo(int uid);
    UserDO selectUserInfoByPhone(String phone);
    int addUser(UserDO userDO);
    int updateUserInfo(UserDO userDO);
    int deleteUser(int uid);
    int updatePhone(UserDO userDO);
    int updatePassword(UserDO userDO);
}
