package org.example.coffee.controller;

import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class AdminController {

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/admin/list")
    public String product(Model model) {

        ArrayList<ProductDTO> list = productDAO.productList();
        model.addAttribute("list", list);

        return "admin_list";
    }
}
