package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;
import org.example.coffee.dto.OrderSummaryDTO;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderDTO findOrderByEmail(String email);

    List<OrderProductDTO> findOrderProductsByEmail(String email);

    OrderSummaryDTO findOrderSummary(String email);
}
