package com.example.niftihub.controller;

import com.example.niftihub.pojo.dto.LoginDTO;
import com.example.niftihub.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;
    @PostMapping("/login")
    public LoginDTO adminLogin(@RequestBody String username, @RequestBody String password){




        return null;
    }


}
