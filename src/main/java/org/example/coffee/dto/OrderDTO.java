package org.example.coffee.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private int orderId;
    private String email;
    private String address;
    private String zipcode;
    private LocalDateTime orderTime;
    private String orderStatus;
    private int totalPrice;

    private List<OrderProductDTO> orderProducts;

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = List.copyOf(orderProducts);
    }
}