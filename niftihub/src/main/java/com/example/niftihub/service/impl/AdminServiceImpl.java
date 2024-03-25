package com.example.niftihub.service.impl;

import com.example.niftihub.dao.AdminMapper;
import com.example.niftihub.service.inter.AdminService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public String login(String username, String password) {
        return adminMapper.login(username,password);
    }

    @Override
    public Integer getLevel(String uid) {
        return adminMapper.selectLevel(uid);
    }
}
