package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.OrderItemDTO;
import org.example.coffee.dto.ProductDTO;

import java.util.ArrayList;

@Mapper
public interface CoffeeMapper {

    ArrayList<ProductDTO> productList();

    int insert(ProductDTO dto);

    int update(ProductDTO dto);

    ProductDTO select(ProductDTO dto);

    int updateProduct(ProductDTO dto);

    int updateImage(ProductDTO dto);

    int deleteProduct(int productId);

    int reduceQuantity(OrderItemDTO orderItemDTO);
}
