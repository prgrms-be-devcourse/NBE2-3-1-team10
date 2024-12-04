package org.example.coffee.controller;

import org.example.coffee.dao.DeliveryDAO;
import org.example.coffee.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DeliveryController {

    @Autowired
    private DeliveryDAO deliveryDAO;

    @GetMapping("/delivery")
    public String delivery(Model model) {
        ArrayList<OrderDTO> lists = deliveryDAO.deliveryList();

        model.addAttribute("lists", lists);
        return "delivery_list";
    }
}
