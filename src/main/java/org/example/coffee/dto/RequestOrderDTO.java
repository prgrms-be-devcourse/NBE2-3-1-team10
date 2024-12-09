package org.example.coffee.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestOrderDTO {
    private int order_id;
    private String email;
    private String address;
    private String zipcode;
    private int total_price;
    private List<RequestOrderProductDTO> orderProducts;
}

