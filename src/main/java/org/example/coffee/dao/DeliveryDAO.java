package org.example.coffee.dao;

import org.example.coffee.dto.OrderDTO;
import org.example.coffee.mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DeliveryDAO {

    @Autowired
    private DeliveryMapper deliveryMapper;

    public ArrayList<OrderDTO> deliveryList() {
        return deliveryMapper.deliveryList();
    }
}
