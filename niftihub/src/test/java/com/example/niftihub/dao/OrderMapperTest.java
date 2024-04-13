package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.OrderDO;
import com.example.niftihub.service.inter.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-16 02:04
 **/
@SpringBootTest
public class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderService orderService;
    @Test
    public void addOrder(){
        OrderDO orderDO=new OrderDO(1,2,"2023-1-14",1,"id",0);
        System.out.println(orderMapper.addOrder(orderDO));
    }
    @Test
    public void updateOrder(){
        OrderDO orderDO=new OrderDO("2023-1-24",0,1);
        System.out.println(orderMapper.updateOrder(orderDO));
    }
    @Test
    public void deleteOrder(){
        System.out.println(orderMapper.deleteOrder(2));
    }
    @Test
    public void selectOrderById(){
        System.out.println(orderMapper.selectOrderById(1));
    }
    @Test
    public void SelectSellerOrder(){
        List<OrderDO> orderDOS=orderMapper.selectSellerOrder(1);
        for (OrderDO o:orderDOS){
            System.out.println(o);
        }

    }
    @Test
    public void SelectBuyerOrder(){
        List<OrderDO> orderDOS=orderMapper.selectBuyerOrder(2);
        for (OrderDO o:orderDOS){
            System.out.println(o);
        }
    }
    @Test
    public void addOrders(){
        String time=new Date().getTime()+"";
        OrderDO orderDO=new OrderDO(1,1,1345,"aedg");
        orderService.addOrder(orderDO);
        System.out.println(orderService.selectOrderByTime(time,1,1).getOid());
    }
}
