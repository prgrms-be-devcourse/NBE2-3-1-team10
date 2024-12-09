package org.example.coffee.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Alias(value = "orderDto")
@Getter
@Setter
@ToString
public class OrderDTO {
    private int order_id;
    private String email;
    private String address;
    private String zipcode;
    private LocalDateTime order_time;
    private String order_status;
    private int total_price;

    private List<OrderProductDTO> orderProducts;

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = List.copyOf(orderProducts);
    }
}