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

        return null;
    }

}
