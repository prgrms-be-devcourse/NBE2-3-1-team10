package org.example.coffee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderProductDTO {
    private int order_id;
    private String imagename;
    private String product_name;
    private String total_product_quantity;
    private int total_product_price;
}
