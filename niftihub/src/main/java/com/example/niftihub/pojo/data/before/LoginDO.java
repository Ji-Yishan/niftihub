package com.example.niftihub.pojo.data.before;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 00:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDO {
    private int uid;
    private String userAccount;
    private String password;

    public LoginDO(String userAccount, String password) {
        this.userAccount = userAccount;
        this.password = password;
    }
}
