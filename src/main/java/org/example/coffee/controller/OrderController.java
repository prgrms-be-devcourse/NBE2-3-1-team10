package org.example.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public String userCheckForm() {
        return "user_check_form";
    }

    @PostMapping
    public String orderList(@RequestParam String email, Model model) {

        return "order_list";
    }
}
