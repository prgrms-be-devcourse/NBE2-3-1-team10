package org.example.coffee.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.OrderDAO;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;
import org.example.coffee.dto.OrderSummaryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("order", order);

        List<OrderProductDTO> orderProducts = orderDAO.findOrderProductsByEmail(email);
        model.addAttribute("orderProducts", orderProducts);

        OrderSummaryDTO orderSummary = orderDAO.findOrderSummary(email);
        model.addAttribute("orderSummary", orderSummary);
        model.addAttribute("email", email);

        return "order_list";
    }

    @GetMapping("/modify")
    public String modifyOrder(String email, Model model) {
        OrderDTO to = new OrderDTO();
        to.setEmail(email);

        to = orderDAO.modifyOrder(to);

        model.addAttribute("to", to);

        return "order_modify";
    }


    @PostMapping("/modify_ok")
    public String modifyOrderOk(HttpServletRequest request, Model model) {
        OrderDTO to = new OrderDTO();
        to.setEmail(request.getParameter("email"));
        to.setAddress(request.getParameter("address"));
        to.setZipcode(request.getParameter("zipcode"));
        System.out.println(to);

        model.addAttribute("flag", orderDAO.modifyOrderOk(to));
        return "order_modify_ok";
    }

//    @DeleteMapping("/order")
//    public String deleteOrder(@RequestParam("order_id") String orderId,
//                              Model model) {
//
//        try {
//            boolean result = orderService.deleteOrder(orderId);
//            model.addAttribute("flag", result ? 0 : 1); // 성공 시 0, 실패 시 1
//        } catch (RuntimeException e) {
//            model.addAttribute("flag", 1); // 실패 플래그 설정
//        }
//        return "order_delete_ok";
//    }
}
