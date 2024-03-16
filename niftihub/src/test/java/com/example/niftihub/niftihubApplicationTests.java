package com.example.niftihub;

import com.example.niftihub.dao.UserMapper;
import com.example.niftihub.pojo.data.UserDO;
import com.example.niftihub.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class niftihubApplicationTests {
@Autowired
    UserService userService;
@Autowired
UserMapper userMapper;

    @Test
    void contextLoads() {
        UserDO userDO=new UserDO("14141414","awet");
        System.out.println(userService.login(userDO));
     }

}
