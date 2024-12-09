package org.example.coffee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final ProductDAO productDAO;

    @RequestMapping("/menu_list")
    public String menu_list(Model model) {

        ArrayList<ProductDTO> menu_list = productDAO.productList();
        model.addAttribute("menu_list", menu_list);

        return "menu_list";
    }
}
