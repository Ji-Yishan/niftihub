package com.example.niftihub.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-03-15 01:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingDO {
    private boolean recognized;
    private boolean showCollection;
    private boolean showDesign;
    private int uid;

    public UserSettingDO(boolean recognized, int uid) {
        this.recognized = recognized;
        this.uid = uid;
    }

    public UserSettingDO(boolean showCollection, boolean showDesign, int uid) {
        this.showCollection = showCollection;
        this.showDesign = showDesign;
        this.uid = uid;
    }
}
