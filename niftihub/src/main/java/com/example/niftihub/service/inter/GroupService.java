package com.example.niftihub.service.inter;

import com.example.niftihub.uitl.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface GroupService {
    //加入群聊
    long joinGroup(String groupUID,String userUID);
    //退出群聊
    long exitGroup(String groupUID,String userUID);
    //查询群聊成员
    Set<Object> getGroupMembers(String groupUID);

}
