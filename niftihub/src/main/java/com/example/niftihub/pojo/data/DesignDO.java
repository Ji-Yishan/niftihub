package com.example.niftihub.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-03-15 01:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesignDO {
    private int did;
    private String name;
    private double price;
    private String info;
    private String brand;
    private String pic;
    private String time;
    private String id;
    private String describe;
    private int right;
    private int amount;
    private int uid;

    public DesignDO(int uid, String name, double price, String info, String brand, String pic, String time, String id, String describe, int right, int amount) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.info = info;
        this.brand = brand;
        this.pic = pic;
        this.time = time;
        this.id = id;
        this.describe = describe;
        this.right = right;
        this.amount = amount;
    }
}
