package com.example.niftihub;

import com.example.niftihub.dao.UserMapper;
import com.example.niftihub.service.inter.UserService;
import com.example.niftihub.uitl.RedisUtil;
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
    RedisUtil redisUtil;


    @Test
    void contextLoads() {

    }

}
