package org.example.coffee.dao;

import lombok.RequiredArgsConstructor;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.mapper.DeliveryMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class DeliveryDAO {
    private final DeliveryMapper deliveryMapper;

    public ArrayList<OrderDTO> deliveryList() {
        return deliveryMapper.deliveryList();
    }

    public int deliveryOk(OrderDTO to) {

        int flag = 2;

        int result = deliveryMapper.updateOrderStatus(to);

        if (result == 0) {
            flag = 1;
        } else if (result == 1) {
            flag = 0;
        }

        return flag;
    }
}
