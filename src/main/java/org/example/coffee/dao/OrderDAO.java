package org.example.coffee.dao;

import lombok.RequiredArgsConstructor;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;
import org.example.coffee.dto.OrderSummaryDTO;
import org.example.coffee.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDAO {

    private final OrderMapper orderMapper;

    public OrderDTO findOrderByEmail(String email) {
        return orderMapper.findOrderByEmail(email);
    }

    public List<OrderProductDTO> findOrderProductsByEmail(String email) {
        return orderMapper.findOrderProductsByEmail(email);
    }

    public OrderSummaryDTO findOrderSummary(String email) {
        return orderMapper.findOrderSummary(email);
    }
}
