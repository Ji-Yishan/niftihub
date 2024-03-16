package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.UserDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Isabella
 * @create: 2024-03-16 00:41
 **/
@SpringBootTest
public class UserInfoMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void addUser(){
        UserDO userDO=new UserDO("test","password");
        System.out.println(userMapper.addUser(userDO));
    }
    @Test
    public void selectUserInfo(){
        System.out.println(userMapper.selectUserInfo(1));
    }
    @Test
    public void updateUserInfo(){
        UserDO userDO=new UserDO(1,"testing2","14141414",null,"234123","awet");
        System.out.println(userMapper.updateUserInfo(userDO));
    }

    @Test
    public void deleteUser(){
        System.out.println(userMapper.deleteUser(3));
    }
}
