package com.example.niftihub;

import com.example.niftihub.dao.UserMapper;
import com.example.niftihub.pojo.data.UserDO;
import com.example.niftihub.service.inter.UserService;
import com.example.niftihub.uitl.UUID;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootTest
@EnableWebSocket
@EnableScheduling
class niftihubApplicationTests {
@Autowired
    UserService userService;
@Autowired
UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(UUID.getUUID());
    }

}
