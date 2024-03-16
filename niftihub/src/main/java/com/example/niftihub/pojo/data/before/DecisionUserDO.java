package com.example.niftihub.pojo.data.before;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-27 20:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecisionUserDO {
    private int uid;
//   todo 不知道为什么查不出来。。可能是getter和setter的问题
    private int deci_id;
    private String title;

}
