package com.example.niftihub.pojo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Isabella
 * @create: 2024-03-17 00:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDO {
    private int ownerId;
    private int did;
    private int sellerId;
}
