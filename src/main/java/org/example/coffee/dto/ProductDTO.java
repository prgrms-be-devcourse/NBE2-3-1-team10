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

    private int product_id;
    private String product_name;
    private int price;
    private int quantity;
    private String imagename;
    private int category_id;
}
