package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.UserSettingDO;

/**
 * @author: Isabella
 * @create: 2024-03-16 19:38
 **/
public interface UserSettingService {
    int updateSetting(UserSettingDO userSettingDO);
    int reconizeUser(UserSettingDO userSettingDO);
    UserSettingDO selectUserSetting(int uid);
}
