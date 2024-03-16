package com.example.niftihub.ws.pojo;

import jakarta.websocket.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineUsers {
    private static final Map<String, Session> onlineUsers = new ConcurrentHashMap<>();
    public Map<String, Session> getOnlineUser(){
        return onlineUsers;
    }
    //添加用户
    public static void addUser(String user,Session session){
        onlineUsers.put(user,session);
    }
    //去除用户
    public static void remove(String key){
        onlineUsers.remove(key);
    }
}
