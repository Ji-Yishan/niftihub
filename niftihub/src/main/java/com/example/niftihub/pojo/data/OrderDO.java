package com.example.niftihub.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-03-15 01:14
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDO {
    private int oid;
    private int sellerId;
    private int buyerId;
    private String time;
    private int did;
    private String id;
    private int status;

    public OrderDO(int sellerId, int buyerId, String time, int did, String id, int status) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.time = time;
        this.did = did;
        this.id = id;
        this.status = status;
    }

    public OrderDO(int sellerId, int buyerId, int did, String id) {
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.did = did;
        this.id = id;
    }

    public OrderDO(String time, int status, int oid) {
        this.time = time;
        this.status = status;
        this.oid=oid;
    }
}
