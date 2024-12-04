package org.example.coffee.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderSummaryDTO {
    private String order_status;
    private String total_order_quantity;
    private String total_order_price;
}
