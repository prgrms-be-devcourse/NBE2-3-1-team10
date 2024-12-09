package org.example.coffee.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestOrderDTO {
    private int orderId;
    private String email;
    private String address;
    private String zipcode;
    private int totalPrice;
    private List<RequestOrderProductDTO> orderProducts;
}

