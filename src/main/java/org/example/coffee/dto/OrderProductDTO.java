
package org.example.coffee.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderProductDTO {
    private String imagename;
    private String productName;
    private String totalProductQuantity;
    private int totalProductPrice;
}
