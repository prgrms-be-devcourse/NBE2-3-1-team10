package org.example.coffee.dao;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;
import org.example.coffee.mapper.OrderMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDAO {
    private final OrderMapper orderMapper;

    public List<OrderDTO> findOrderByEmail(String email) {
        return orderMapper.findOrderByEmail(email);
    }

    public List<OrderProductDTO> findOrderProducts(int orderId) {
        return orderMapper.findOrderProducts(orderId);
    }

    public OrderDTO modifyOrder(OrderDTO to) {
        return orderMapper.modify(to);
    }

    public int modifyOrderOk(OrderDTO to) {
        int flag = 2;

        int result = orderMapper.modifyOk(to);

        if (result == 0) {
            flag = 1;
        } else if (result == 1) {
            flag = 0;
        }

        return flag;
    }

    public int delete(OrderDTO dto) {

        int flag = 1;

        int result = orderMapper.delete(dto);

        if (result == 1) {
            flag = 0;
        }

        return flag;
    }

    public void add(OrderDTO dto) {
        orderMapper.add(dto);
    }
}
