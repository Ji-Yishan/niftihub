package com.example.niftihub.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDO {
    int uid;
    String username;
    String password;
    int level;
    int createUser;
    Boolean isValid;

}
