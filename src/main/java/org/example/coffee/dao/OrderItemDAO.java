package org.example.coffee.dao;

import org.example.coffee.dto.OrderItemDTO;
import org.example.coffee.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrderItemDAO {

    @Autowired
    private OrderItemMapper orderItemMapper;

    public ArrayList<OrderItemDTO> getOrderItems(OrderItemDTO dto) {

        return orderItemMapper.getOrderItems(dto);
    }

    public int delete(OrderItemDTO dto) {

        int flag = 1;

        int result = orderItemMapper.delete(dto);

        if (result == 1) {
            flag = 0;
        }

        return flag;
    }
}