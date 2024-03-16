package com.example.niftihub.controller;

import com.example.niftihub.common.Result;
import com.example.niftihub.pojo.data.UserSettingDO;
import com.example.niftihub.service.inter.UserSettingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Isabella
 * @create: 2024-03-17 01:03
 **/
@RestController
public class UserSettingController {
    @Autowired
    UserSettingService userSettingService;
    @Autowired
    Result result;
    @PostMapping("/{uid}/setting")
    public String updateSetting(@RequestBody UserSettingDO userSettingDO) throws JsonProcessingException {
        userSettingService.updateSetting(userSettingDO);
        return result.gainPostSuccess();
    }
}
