package com.example.niftihub.service.impl;

import com.example.niftihub.service.inter.GroupService;
import com.example.niftihub.uitl.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    RedisUtil redisUtil;
    @Override
    public long joinGroup(String groupUID, String userUID) {
        return redisUtil.sSet("nifthub-group-"+groupUID,userUID);
    }

    @Override
    public long exitGroup(String groupUID, String userUID) {
        return redisUtil.setRemove("nifthub-group-"+groupUID,userUID);
    }

    @Override
    public Set<Object> getGroupMembers(String groupUID) {
        return redisUtil.sGet("nifthub-group-"+groupUID);
    }
}
