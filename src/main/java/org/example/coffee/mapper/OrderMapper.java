package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderDTO;

@Mapper
public interface OrderMapper {

    int delete(OrderDTO dto);
}
