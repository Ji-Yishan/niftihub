package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.UserSettingDO;
import com.example.niftihub.pojo.dto.UserSettingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    List<UserSettingDO> selectAllUser(int offset);
}
