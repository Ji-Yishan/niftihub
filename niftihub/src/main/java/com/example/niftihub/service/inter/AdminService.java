package com.example.niftihub.service.inter;

public interface AdminService {
    String login(String username,String password);
    Integer getLevel(String uid);
}
