package org.example.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

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
    private String order_time;
    private String order_status;
    private int total_price;
}