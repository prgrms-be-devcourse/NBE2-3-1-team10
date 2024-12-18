package org.example.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.coffee.dao.DeliveryDAO;
import org.example.coffee.dto.OrderDTO;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryDAO deliveryDAO;

    @GetMapping("/delivery")
    public String delivery(Model model) {
        ArrayList<OrderDTO> lists = deliveryDAO.deliveryList();

        model.addAttribute("lists", lists);
        return "delivery_list";
    }

    @RequestMapping("/delivery_ok")
    public String deliveryOk(HttpServletRequest request, Model model) {

        System.out.println("[controller] delivery_ok is called");
        String orderIdsParam = request.getParameter("orderIds");


        if (orderIdsParam != null && !orderIdsParam.isEmpty()) {

            orderIdsParam = orderIdsParam.replace("[", "").replace("]", "");
            String[] orderIds = orderIdsParam.split(",");

            ArrayList<Integer> failedIds = new ArrayList<>();  // 실패한 id

            for (String orderId : orderIds) {
                OrderDTO to = new OrderDTO();
                to.setOrderId(Integer.parseInt(orderId.trim()));
                int result = deliveryDAO.deliveryOk(to);

                if (result != 0) {
                    failedIds.add(to.getOrderId());
                }

            }
            model.addAttribute("flag", failedIds.isEmpty() ? 0 : 1);
            model.addAttribute("failedIds", failedIds);

        }
        return "delivery_ok";

    }
}
