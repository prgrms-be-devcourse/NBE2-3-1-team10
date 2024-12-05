package org.example.coffee.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.coffee.dao.OrderDAO;
import org.example.coffee.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @GetMapping("/order")
    public String order() {
        return "order_list";
    }

    @GetMapping("/user")
    public String user() {
        return "user_check_form";
    }

    @GetMapping("/order/modify")
    public String modifyOrder(String email, Model model) {
        OrderDTO to = new OrderDTO();
        to.setEmail(email);

        to = orderDAO.modifyOrder(to);

        model.addAttribute("to", to);

        return "order_modify";
    }

    @PostMapping("/order/modify_ok")
    public String modifyOrderOk(HttpServletRequest request, Model model) {
        OrderDTO to = new OrderDTO();
        to.setEmail(request.getParameter("email"));
        to.setAddress(request.getParameter("address"));
        to.setZipcode(request.getParameter("zipcode"));
        System.out.println(to);

        model.addAttribute("flag", orderDAO.modifyOrderOk(to));

        return "order_modify_ok";
    }

}
