package org.example.coffee.dto;

import lombok.*;

@Getter
@Setter
public class OrderItemDTO {
    private int seq;
    private int orderId;
    private int productId;
    private int count;

}