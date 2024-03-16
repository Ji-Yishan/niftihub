package com.example.niftihub.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-03-17 01:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String token;
    private int uid;
}
