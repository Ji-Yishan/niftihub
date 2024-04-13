package com.example.niftihub.controller;

import com.example.niftihub.common.Result;
import com.example.niftihub.pojo.data.OrderDO;
import com.example.niftihub.pojo.dto.OrderDTO;
import com.example.niftihub.service.inter.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author: Isabella
 * @create: 2024-04-14 00:30
 **/
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    Result result;

    @GetMapping("/order/{oid}")
    public OrderDO orderDO(@RequestParam int oid){
        return orderService.selectOrderById(oid);
    }

    @DeleteMapping("/order/{oid}")
    public String deleteOrder(@RequestParam int oid) throws JsonProcessingException {
        orderService.deleteOrder(oid);
        return result.gainDeleteSuccess();
    }

    @PostMapping("/order")
    public int addOrder(@RequestBody OrderDTO orderDTO) throws JsonProcessingException {
        int sellerId=orderDTO.getSellerId();
        int buyerId=orderDTO.getBuyerId();
        OrderDO orderDO=new OrderDO(sellerId,buyerId,orderDTO.getDid(),orderDTO.getId());
        orderService.addOrder(orderDO);
        String time=new Date().getTime()+"";
        orderDO.setTime(time);
        OrderDO orderDO1=orderService.selectOrderByTime(time,sellerId,buyerId);
        return orderDO1.getOid();
//        todo 和corda链接提供返回状态等信息
    }

}
