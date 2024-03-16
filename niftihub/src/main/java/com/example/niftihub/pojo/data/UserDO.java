package com.example.niftihub.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 17:30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    private int uid;
    private String username;
    private String phone;
    private String description;
    private String profileP;
    private String password;

    public UserDO(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public UserDO(int uid, String phone) {
        this.uid = uid;
        this.phone = phone;
    }
}
