package org.example.coffee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.ProductDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class MenuController {
    private final ProductDAO productDAO;

    @GetMapping("/")
    public String menuList(Model model) {
        ArrayList<ProductDTO> menu_list = productDAO.productList();
        model.addAttribute("menu_list", menu_list);

        return "menu_list";
    }
}
