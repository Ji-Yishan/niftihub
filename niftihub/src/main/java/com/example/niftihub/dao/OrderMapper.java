package com.example.niftihub.dao;

import com.example.niftihub.pojo.data.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Isabella
 * @create: 2024-03-15 15:18
 **/
@Mapper
public interface OrderMapper {
    List<OrderDO> selectSellerOrder(int uid);
    List<OrderDO> selectBuyerOrder(int uid);
    int addOrder(OrderDO orderDO);
    int updateOrder(OrderDO orderDO);
    int deleteOrder(int oid);
    OrderDO selectOrderById(int oid);
    OrderDO selectOrderByTime(@Param("time") String time,
                              @Param("sellerId") int sellerId,@Param("buyerId") int buyerId);
}
