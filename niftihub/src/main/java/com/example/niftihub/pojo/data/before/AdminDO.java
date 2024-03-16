package com.example.niftihub.pojo.data.before;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 17:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDO {
    private int aid;
    private String adminAccount;
    private String adminPwd;
}
