package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderDTO;

@Mapper
public interface OrderMapper {

    OrderDTO modify(OrderDTO to);
    int modifyOk(OrderDTO to);
}
