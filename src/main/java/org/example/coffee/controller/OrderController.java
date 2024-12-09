package org.example.coffee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.OrderDAO;

import org.example.coffee.dao.OrderItemDAO;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.*;
import org.example.coffee.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDAO orderDAO;
    private final OrderService orderService;
    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;

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
            orderService.deleteOrder(orderId);
            model.addAttribute("flag", 0); // 성공 시 0, 실패 시 1
        } catch (RuntimeException e) {
            model.addAttribute("flag", 1); // 실패 플래그 설정
        }

        return "order_delete_ok";
    }

    @PostMapping("/order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody RequestOrderDTO request, Model model) {
        Map<String, Object> response = new HashMap<>();
        int flag = 0;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setEmail(request.getEmail());
        orderDTO.setAddress(request.getAddress());
        orderDTO.setZipcode(request.getZipcode());
        orderDTO.setOrder_time(LocalDateTime.now());
        orderDTO.setOrder_status("출고 전");
        orderDTO.setTotal_price(request.getTotal_price());
        orderDAO.add(orderDTO);

        System.out.println(orderDTO.getOrder_id());
        for (RequestOrderProductDTO requestOrderProductDTO : request.getOrderProducts()) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setOrder_id(orderDTO.getOrder_id());
            orderItemDTO.setProduct_id(requestOrderProductDTO.getProductId());
            orderItemDTO.setCount(requestOrderProductDTO.getQuantity());

            flag = orderItemDAO.insert(orderItemDTO);
            productDAO.reduceQuantity(orderItemDTO);

            if (flag == 1) {
                response.put("success", false);
                response.put("message", "존재하지 않는 상품입니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }

        response.put("success", true);
        response.put("message", "Order created successfully");
        return ResponseEntity.ok(response);
    }
}