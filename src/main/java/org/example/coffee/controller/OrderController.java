package org.example.coffee.controller;

import org.example.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @DeleteMapping("/order")
    public String deleteOrder(@RequestParam("order_id") String orderId,
                              Model model) {

        boolean success = orderService.deleteOrder(orderId);

        model.addAttribute("flag", success ? 0 : 1);

        return "order_delete_ok";
    }
}
