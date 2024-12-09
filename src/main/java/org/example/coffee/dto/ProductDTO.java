package org.example.coffee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias(value = "dto")
@Getter
@Setter
@ToString
public class ProductDTO {

    private int productId;
    private String productName;
    private int price;
    private int quantity;
    private String imagename;
    private int categoryId;
    private boolean deleted;
}
