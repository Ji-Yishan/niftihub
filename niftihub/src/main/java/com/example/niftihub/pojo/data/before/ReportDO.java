package com.example.niftihub.pojo.data.before;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2023-09-23 18:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDO {
    private int rid;
    private int pid;
    private String reason;
    private boolean result;
}
