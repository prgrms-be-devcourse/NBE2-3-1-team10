package org.example.coffee.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias( value = "orderDto")
@Getter
@Setter
public class OrderDTO {
    private int order_id;
    private String email;
    private String address;
    private String zipcode;
    private String order_time;
    private String order_status;
    private int total_price;
}