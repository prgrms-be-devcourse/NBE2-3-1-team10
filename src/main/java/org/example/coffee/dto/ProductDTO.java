package org.example.coffee.dto;

import lombok.*;

@Getter
@Setter
public class ProductDTO {
    private int productId;
    private String productName;
    private int price;
    private int quantity;
    private String imagename;
    private int categoryId;
    private boolean deleted;
}
