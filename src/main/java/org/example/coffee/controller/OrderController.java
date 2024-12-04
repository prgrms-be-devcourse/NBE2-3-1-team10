package org.example.coffee.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.OrderDAO;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;
import org.example.coffee.dto.OrderSummaryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDAO orderDAO;

    @GetMapping
    public String userCheckForm() {
        return "user_check_form";
    }

    @PostMapping
    public String orderList(@RequestParam String email, Model model) {
        OrderDTO order = orderDAO.findOrderByEmail(email);
        if (order == null) {
            return "redirect:/orders";
        }

        List<OrderProductDTO> orderProducts = orderDAO.findOrderProductsByEmail(email);
        model.addAttribute("orderProducts", orderProducts);

        OrderSummaryDTO orderSummary = orderDAO.findOrderSummary(email);
        model.addAttribute("orderSummary", orderSummary);

        return "order_list";
    }
}
