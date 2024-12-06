package org.example.coffee.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.OrderDAO;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderProductDTO;
import org.example.coffee.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDAO orderDAO;
    private final OrderService orderService;

    @GetMapping
    public String userCheckForm() {
        return "user_check_form";
    }

    @PostMapping
    public String orderList(
            @RequestParam String email,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        List<OrderDTO> orders = orderDAO.findOrderByEmail(email);
        if (orders.isEmpty()) {
            redirectAttributes.addAttribute("notExist", true);
            return "redirect:/orders";
        }

        for (OrderDTO order : orders) {
            List<OrderProductDTO> orderProducts = orderDAO.findOrderProducts(order.getOrder_id());
            order.setOrderProducts(orderProducts);
        }
        model.addAttribute("orders", orders);

        return "order_list";
    }

    @GetMapping("/modify")
    public String modifyOrder(int order_id, Model model) {
        OrderDTO to = new OrderDTO();
        to.setOrder_id(order_id);

        to = orderDAO.modifyOrder(to);

        model.addAttribute("to", to);

        return "order_modify";
    }


    @PostMapping("/modify_ok")
    public String modifyOrderOk(HttpServletRequest request, Model model) {
        OrderDTO to = new OrderDTO();
        to.setOrder_id(Integer.parseInt(request.getParameter("order_id")));
        to.setAddress(request.getParameter("address"));
        to.setZipcode(request.getParameter("zipcode"));
        System.out.println(to);

        model.addAttribute("flag", orderDAO.modifyOrderOk(to));
        return "order_modify_ok";
    }


    @GetMapping("/delete")
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
