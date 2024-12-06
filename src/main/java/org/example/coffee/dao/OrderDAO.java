package org.example.coffee.dao;

import org.example.coffee.dto.OrderDTO;
import org.example.coffee.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

    @Autowired
    private OrderMapper orderMapper;

    public int delete(OrderDTO dto) {

        int flag = 1;

        int result = orderMapper.delete(dto);

        if (result == 1) {
            flag = 0;
        }

        return flag;
    }
}
