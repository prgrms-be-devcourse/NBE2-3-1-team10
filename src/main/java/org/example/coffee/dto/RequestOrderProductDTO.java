package org.example.coffee.dto;

import lombok.*;

@Getter
@Setter
public class RequestOrderProductDTO {
    private int productId;
    private int stock;
    private String productName;
    private int price;
    private int quantity;
}
