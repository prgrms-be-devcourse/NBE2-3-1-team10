package org.example.coffee.dao;

import org.example.coffee.dto.OrderDTO;
import org.example.coffee.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OrderDAO {

    @Autowired
    private OrderMapper orderMapper;

    public OrderDTO modifyOrder(OrderDTO to) {
        return orderMapper.modify(to);
    }

    public int modifyOrderOk(OrderDTO to) {
        int flag = 2;

        int result = orderMapper.modifyOk(to);

        if( result == 0 ) {
            flag = 1;
        } else if( result == 1 ) {
            flag = 0;
        }

        return flag;
    }
}
