
package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderDTO> findOrderByEmail(String email);

    List<OrderProductDTO> findOrderProducts(int orderId);

    OrderDTO modify(OrderDTO to);

    int modifyOk(OrderDTO to);

    int delete(OrderDTO dto);

    void add(OrderDTO to);
}
