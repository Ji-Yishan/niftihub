package com.example.niftihub.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author: Isabella
 * @create: 2024-03-16 23:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesignDTO {
    private int did;
    private String name;
    private double price;
    private String info;
    private String brand;
    private String pic;
    private String time;
    private String describe;
    private int right;
    private int amount;
    private int uid;

    public DesignDTO(int did, String name, double price, String info, String brand, String pic, String time, String describe, int right, int amount) {
        this.did = did;
        this.name = name;
        this.price = price;
        this.info = info;
        this.brand = brand;
        this.pic = pic;
        this.time = time;
        this.describe = describe;
        this.right = right;
        this.amount = amount;
    }
}
