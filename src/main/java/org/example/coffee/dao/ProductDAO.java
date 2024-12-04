package org.example.coffee.dao;

import org.example.coffee.dto.ProductDTO;
import org.example.coffee.mapper.CoffeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ProductDAO {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public ArrayList<ProductDTO> productList() {

        ArrayList<ProductDTO> list = coffeeMapper.productList();

        return list;
    }

    public int insert(ProductDTO dto) {

        int flag = 1;

        int result = coffeeMapper.insert(dto);

        if (result == 1) {
            flag = 0;
        }

        return flag;
    }

    public ProductDTO getProduct(ProductDTO to) {
        return coffeeMapper.select(to);
    }

}
