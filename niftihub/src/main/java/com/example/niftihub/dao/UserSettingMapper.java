package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.UserSettingDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Isabella
 * @create: 2024-03-15 15:21
 **/
@Mapper
public interface UserSettingMapper {
    int updateSetting(UserSettingDO userSettingDO);
    int reconizeUser(UserSettingDO userSettingDO);
    int addSetting(UserSettingDO userSettingDO);
    UserSettingDO selectUserSetting(int uid);
}
