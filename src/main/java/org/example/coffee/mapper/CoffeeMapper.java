package org.example.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.coffee.dto.ProductDTO;

import java.util.ArrayList;

@Mapper
public interface CoffeeMapper {

    ArrayList<ProductDTO> productList();

    int insert(ProductDTO dto);

    ProductDTO select(ProductDTO dto);
}
