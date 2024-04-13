package com.example.niftihub.service.inter;

import com.example.niftihub.pojo.data.OrderDO;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-16 19:39
 **/
public interface OrderService {
    List<OrderDO> selectSellerOrder(int uid);
    List<OrderDO> selectBuyerOrder(int uid);
    int addOrder(OrderDO orderDO);
    int updateOrder(OrderDO orderDO);
    int deleteOrder(int oid);
    OrderDO selectOrderById(int oid);
    OrderDO selectOrderByTime(String time,int sellerId,int buyerId);
}
