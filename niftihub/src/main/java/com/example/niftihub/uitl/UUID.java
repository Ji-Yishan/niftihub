package com.example.niftihub.uitl;

public class UUID {
    public static String getUUID(){
        return java.util.UUID.randomUUID().toString().replaceAll("-","");
    }
}
