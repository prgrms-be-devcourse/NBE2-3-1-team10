package org.example.coffee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias(value = "orderItemDto")
@Getter
@Setter
@ToString
public class OrderItemDTO {
    private int seq;
    private int order_id;
    private int product_id;
    private int count;

}