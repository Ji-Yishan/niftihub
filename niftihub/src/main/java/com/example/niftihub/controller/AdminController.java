package com.example.niftihub.controller;

import cn.hutool.json.JSONUtil;
import com.example.niftihub.pojo.data.AdminDO;
import com.example.niftihub.pojo.dto.AdminDTO;
import com.example.niftihub.pojo.dto.LoginDTO;
import com.example.niftihub.service.impl.AdminServiceImpl;
import com.example.niftihub.uitl.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.ServerError;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;
    private static final String tokenHeader = "Authorization";
    @PostMapping("/login")
    public AdminDTO adminLogin(@RequestBody AdminDO adminDO){
        //AdminDO adminDO = JSONUtil.toBean(json,AdminDO.class);
        AdminDTO adminDTO = adminService.login(adminDO.getUsername(),adminDO.getPassword());
        //todo 用户名或密码错误
        if(adminDTO == null){
            log.error("用户名或密码错误");
            throw new RuntimeException("用户名或密码错误");
        }else{
            log.info("正确");
        }
        String token = JwtUtils.createAdminToken(adminDTO.getUid(),adminDTO.getLevel());
        adminDTO.setToken(token);
        return adminDTO;
    }
    @PostMapping("/register")
    public AdminDTO adminRegister(@RequestBody AdminDO adminDO, HttpServletRequest request){
        if(adminDO.getUsername() == null || adminDO.getUsername().equals("")){
            log.error("用户名为空");
            throw new RuntimeException("用户名为空");
        }
        if(adminDO.getPassword() == null || adminDO.getPassword().equals("")){
            throw new RuntimeException("密码为空");
        }
        if(adminService.selectUsername(adminDO.getUsername()) != null){
            throw new RuntimeException("用户名存在");
        }
        String token = request.getHeader(tokenHeader);
        Claims claims = JwtUtils.parseJWT(token);
        adminDO.setCreateUser((int)claims.get("uid"));
        int adminLevel = (int)claims.get("adminLevel");
        if(adminLevel < adminDO.getLevel()){
            log.error("权限不足");
            throw new RuntimeException("权限不足");
        }
        adminService.addUser(adminDO);
        AdminDTO adminDTO = adminService.login(adminDO.getUsername(),adminDO.getPassword());
        String newToken = JwtUtils.createAdminToken(adminDTO.getUid(),adminDTO.getLevel());
        adminDTO.setToken(newToken);
        return adminDTO;
    }


}
