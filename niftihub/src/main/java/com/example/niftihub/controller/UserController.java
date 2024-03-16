package com.example.niftihub.controller;

import com.example.niftihub.common.Result;
import com.example.niftihub.common.ResultCode;
import com.example.niftihub.pojo.data.UserDO;
import com.example.niftihub.pojo.dto.LoginDTO;
import com.example.niftihub.service.inter.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Isabella
 * @create: 2024-03-17 01:02
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    Result result;

    @PostMapping("/register")
    public LoginDTO register(@RequestBody UserDO userDO){
        int uid=userService.addUser(userDO);
//        todo 换成真的token
        String token="temp token";
        LoginDTO loginDTO=new LoginDTO(token,uid);
        return loginDTO;
    }

    @PostMapping("/login")
    public LoginDTO login(@RequestBody UserDO userDO){
        int uid=userService.login(userDO);
//        todo 换成真的token
        String token="temp token";
        LoginDTO loginDTO=new LoginDTO(token,uid);
        return loginDTO;
    }
    @PutMapping("/{uid}/personal/info")
    public String updateInfo(@RequestBody UserDO userDO, @PathVariable int uid) throws JsonProcessingException {
        userDO.setUid(uid);
        userService.updateUserInfo(userDO);
        return result.gainPostSuccess();
    }
    @GetMapping("/{uid}/personal/info")
    public UserDO selectInfo(@PathVariable int uid){
        return userService.selectUserInfo(uid);
    }
}
