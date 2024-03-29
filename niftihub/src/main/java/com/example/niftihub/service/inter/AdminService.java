package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.AdminDO;
import com.example.niftihub.pojo.dto.AdminDTO;

public interface AdminService {
    AdminDTO login(String username, String password);
    Integer getLevel(String uid);
    Integer selectUsername(String username);
    Integer addUser(AdminDO adminDO);
}
