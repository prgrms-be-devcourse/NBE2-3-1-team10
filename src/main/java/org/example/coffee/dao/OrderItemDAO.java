
package org.example.coffee.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import org.example.coffee.dto.OrderItemDTO;
import org.example.coffee.mapper.OrderItemMapper;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class OrderItemDAO {
    private final OrderItemMapper orderItemMapper;

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

    public int insert(OrderItemDTO dto) {
        int flag = 1;
        int result = orderItemMapper.insert(dto);

        if (result == 1) {
            flag = 0;
        }

        return flag;
    }
}
