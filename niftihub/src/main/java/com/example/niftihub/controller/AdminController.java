package com.example.niftihub.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.niftihub.pojo.data.AdminDO;
import com.example.niftihub.pojo.data.UserSettingDO;
import com.example.niftihub.pojo.dto.AdminDTO;
import com.example.niftihub.pojo.dto.UserSettingDTO;
import com.example.niftihub.service.impl.AdminServiceImpl;
import com.example.niftihub.service.impl.UserSettingServiceImpl;
import com.example.niftihub.service.inter.UserSettingService;
import com.example.niftihub.uitl.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    UserSettingServiceImpl userSettingService;
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
        if(!adminDTO.getIsValid()){
            log.error("账号无效");
            throw new RuntimeException("账号无效");
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
        adminDO.setIsValid(true);
        adminService.addUser(adminDO);
        AdminDTO adminDTO = adminService.login(adminDO.getUsername(),adminDO.getPassword());
        String newToken = JwtUtils.createAdminToken(adminDTO.getUid(),adminDTO.getLevel());
        adminDTO.setToken(newToken);
        return adminDTO;
    }
    @GetMapping("/user/{userUid}")
    public UserSettingDTO getSetting(@PathVariable int userUid,HttpServletRequest request){
        if(judgmentPermissions(request)){
            log.error("权限不足");
            throw new RuntimeException("权限不足");
        }
        UserSettingDO userSettingDO = userSettingService.selectUserSetting(userUid);
        if(userSettingDO == null){
            log.error("无此用户");
            throw new RuntimeException("无此用户");
        }
        return new UserSettingDTO(userSettingDO);
    }

    @GetMapping("/allUser")
    public List<UserSettingDO> getAllSetting(@RequestBody String json,HttpServletRequest request){
        if(judgmentPermissions(request)){
            log.error("权限不足");
            throw new RuntimeException("权限不足");
        }
        int offset = (int)JSONUtil.getByPath(JSONUtil.parse(json),"offset");
        return userSettingService.selectAllUser(offset);
    }
    @PostMapping("/setUser/{userUid}")
    public UserSettingDTO setUser(@RequestBody String json,@PathVariable int userUid){
        UserSettingDO userSettingDO = JSONUtil.toBean(json, UserSettingDO.class);
        userSettingService.updateSetting(userSettingDO);
        return new UserSettingDTO(userSettingService.selectUserSetting(userUid));
    }

    @PostMapping("/setRecognized/{userUid}")
    public UserSettingDTO setRecognized(@RequestBody String json,@PathVariable int userUid){
        JSONObject jsonObject = JSONUtil.parseObj(json);
        UserSettingDO userSettingDO = userSettingService.selectUserSetting(userUid);
        userSettingDO.setRecognized((Boolean) jsonObject.get("recognized"));
        userSettingDO.setUid(userUid);
        userSettingService.reconizeUser(userSettingDO);
        return new UserSettingDTO(userSettingDO);
    }


    private int getLevel(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        Claims claims = JwtUtils.parseJWT(token);
        return (int)claims.get("adminLevel");
    }

    /***
     * 给未来拓展用的，暂时没有用
     * @param request
     * @return 权限不足返回false
     */
    private boolean judgmentPermissions(HttpServletRequest request){
        int level = getLevel(request);
        return level <= 1;
    }




}
