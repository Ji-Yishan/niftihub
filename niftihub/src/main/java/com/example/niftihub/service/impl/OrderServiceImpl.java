package com.example.niftihub.service.impl;

import com.example.niftihub.dao.OrderMapper;
import com.example.niftihub.pojo.data.OrderDO;
import com.example.niftihub.service.inter.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-17 00:59
 **/
@Service
public class OrderServiceImpl implements OrderService {
//    todo 账单这智能合约写完之后要能接着改
    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<OrderDO> selectSellerOrder(int uid) {
        return orderMapper.selectSellerOrder(uid);
    }

    @Override
    public List<OrderDO> selectBuyerOrder(int uid) {
        return orderMapper.selectBuyerOrder(uid);
    }

    @Override
    public int addOrder(OrderDO orderDO) {

        return orderMapper.addOrder(orderDO);
    }

    @Override
    public int updateOrder(OrderDO orderDO) {
        long date=new Date().getTime();
        orderDO.setTime(date+"");
        return orderMapper.updateOrder(orderDO);
    }

    @Override
    public int deleteOrder(int oid) {
        return orderMapper.deleteOrder(oid);
    }

    @Override
    public OrderDO selectOrderById(int oid) {
        return orderMapper.selectOrderById(oid);
    }

    @Override
    public OrderDO selectOrderByTime(String time,int sellerId,int buyerId) {
        return orderMapper.selectOrderByTime(time,sellerId,buyerId);
    }
}
