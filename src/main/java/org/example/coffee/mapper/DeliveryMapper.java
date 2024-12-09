package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderDTO;

import java.util.ArrayList;

@Mapper
public interface DeliveryMapper {

    ArrayList<OrderDTO> deliveryList();

    int updateOrderStatus(OrderDTO to);
}
