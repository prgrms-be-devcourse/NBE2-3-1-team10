package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderItemDTO;

import java.util.ArrayList;

@Mapper
public interface OrderItemMapper {

    ArrayList<OrderItemDTO> getOrderItems(OrderItemDTO dto);

    int delete(OrderItemDTO dto);
}
