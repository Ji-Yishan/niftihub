package com.example.niftihub;

import com.example.niftihub.dao.AdminMapper;
import com.example.niftihub.dao.UserMapper;
import com.example.niftihub.pojo.data.UserDO;
import com.example.niftihub.service.impl.AdminServiceImpl;
import com.example.niftihub.service.inter.MessageService;
import com.example.niftihub.service.inter.UserService;
import com.example.niftihub.uitl.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableScheduling
class niftihubApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MessageService messageService;
    @Autowired
    AdminServiceImpl adminService;


    @Test
    void contextLoads() {

        String token =  JwtUtils.createUserToken("12345678910");
        System.out.println(JwtUtils.getLevel(token));
    }

}
