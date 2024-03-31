package com.example.niftihub.pojo.dto;

import com.example.niftihub.pojo.data.UserSettingDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingDTO {
    int uid;
    boolean recognized;
    boolean showCollection;
    boolean showDesign;

    public UserSettingDTO(UserSettingDO userSettingDO){
        this.uid = userSettingDO.getUid();
        this.recognized = userSettingDO.isRecognized();
        this.showCollection = userSettingDO.isShowCollection();
        this.showDesign = userSettingDO.isShowDesign();
    }
    
}
