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

        try {
            boolean result = orderService.deleteOrder(orderId);
            model.addAttribute("flag", result ? 0 : 1); // 성공 시 0, 실패 시 1
        } catch (RuntimeException e) {
            model.addAttribute("flag", 1); // 실패 플래그 설정
        }

        return "order_delete_ok";
    }
}
