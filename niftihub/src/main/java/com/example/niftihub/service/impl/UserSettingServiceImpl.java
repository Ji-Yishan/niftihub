package com.example.niftihub.service.impl;

import com.example.niftihub.dao.UserSettingMapper;
import com.example.niftihub.pojo.data.UserSettingDO;
import com.example.niftihub.service.inter.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-16 23:31
 **/
@Service
public class UserSettingServiceImpl implements UserSettingService {
    @Autowired
    UserSettingMapper userSettingMapper;

    @Override
    public int updateSetting(UserSettingDO userSettingDO) {
        return userSettingMapper.updateSetting(userSettingDO);
    }

    @Override
    public int reconizeUser(UserSettingDO userSettingDO) {
        return userSettingMapper.reconizeUser(userSettingDO);
    }

    @Override
    public UserSettingDO selectUserSetting(int uid) {
        return userSettingMapper.selectUserSetting(uid);
    }

    @Override
    public List<UserSettingDO> selectAllUser(int offset) {
        return userSettingMapper.selectAllUser(offset);
    }
}
