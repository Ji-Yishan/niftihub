package com.example.niftihub.service.impl;

import com.example.niftihub.dao.UserMapper;
import com.example.niftihub.dao.UserSettingMapper;
import com.example.niftihub.pojo.data.UserDO;
import com.example.niftihub.pojo.data.UserSettingDO;
import com.example.niftihub.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Isabella
 * @create: 2024-03-16 19:40
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserSettingMapper userSettingMapper;

    @Override
    public int login(UserDO userDO) {
        UserDO user=userMapper.login(userDO);
        if(user.getPassword().equals(userDO.getPassword())){
            log.info("登录成功");
            return user.getUid();
        }
        log.info("登录失败，账号密码不匹配");
        return 0;
    }

    @Override
    public UserDO selectUserInfo(int uid) {
        return userMapper.selectUserInfo(uid);
    }

    @Override
    public int addUser(UserDO userDO) {
        userMapper.addUser(userDO);
        UserDO userDO1=userMapper.login(userDO);
        int uid=userDO1.getUid();
        UserSettingDO userSettingDO=new UserSettingDO(false,true,true,uid);
        userSettingMapper.addSetting(userSettingDO);
        return uid;
    }

    @Override
    public int updateUserInfo(UserDO userDO) {
        return userMapper.updateUserInfo(userDO);
    }

    @Override
    public int deleteUser(int uid) {
//        todo 这里最好有个验证吧但是还没想好怎么验证
        return userMapper.deleteUser(uid);
    }

    @Override
    public int updatePhone(UserDO userDO) {
        return userMapper.updatePhone(userDO);
    }

    @Override
    public int updatePassword(UserDO userDO) {
        return userMapper.updatePassword(userDO);
    }
}
