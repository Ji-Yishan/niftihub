package com.example.niftihub;

import com.example.niftihub.dao.UserMapper;
import com.example.niftihub.pojo.data.UserDO;
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


    @Test
    void contextLoads() {
        UserDO userDO = new UserDO(12,"小明","1433223",null,null,"passwd");
        String jwt =  JwtUtils.createToken(10*1000,userDO,"Admin");
        System.out.println(JwtUtils.parseJWT(jwt));

        //System.out.println(tokenFilter.matchUrl("/test/asdsasd/asd"));


    }

}
