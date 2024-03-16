package com.example.niftihub.uitl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Isabella
 * @create: 2023-10-01 23:11
 **/
public class Common {
    public static String getTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime now = LocalDateTime.now();
        String time=now.format(dateTimeFormatter);
        return time;
    }
}
