package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.UserSettingDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Isabella
 * @create: 2024-03-16 00:59
 **/
@SpringBootTest
public class UserSettingMapperTest {
    @Autowired
    UserSettingMapper userSettingMapper;
    @Test
    public void addSetting(){
        UserSettingDO userSettingDO=new UserSettingDO(true,1);
        System.out.println(userSettingMapper.addSetting(userSettingDO));
    }
    @Test
    public void selectSetting(){
        System.out.println(userSettingMapper.selectUserSetting(1));
    }
    @Test
    public void  updateSetting(){
        UserSettingDO userSettingDO=new UserSettingDO(false,false,1);
        System.out.println(userSettingMapper.updateSetting(userSettingDO));
    }
    @Test
    public void reconizeUser(){
        UserSettingDO userSettingDO=new UserSettingDO(false,1);
        System.out.println(userSettingMapper.reconizeUser(userSettingDO));
    }
}
