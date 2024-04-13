package com.example.niftihub.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-04-14 00:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int sellerId;
    private int buyerId;
    private int did;
    private String id;

}
