package org.example.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.coffee.dao.OrderDAO;
import org.example.coffee.dao.OrderItemDAO;
import org.example.coffee.dao.ProductDAO;
import org.example.coffee.dto.OrderDTO;
import org.example.coffee.dto.OrderItemDTO;
import org.example.coffee.dto.ProductDTO;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;

    @Transactional
    public void deleteOrder(String orderId) {

        try {
            OrderItemDTO OiDto = new OrderItemDTO();
            OiDto.setOrderId(Integer.parseInt(orderId));

            ArrayList<OrderItemDTO> orderItemList = orderItemDAO.getOrderItems(OiDto);

            for (OrderItemDTO dto : orderItemList) {

                int productId = dto.getProductId();
                int count = dto.getCount();
                int seq = dto.getSeq();

                ProductDTO pDto = new ProductDTO();
                pDto.setProductId(productId);
                pDto.setQuantity(count);

                int productFlag = productDAO.update(pDto);
                if (productFlag != 0) {
                    throw new RuntimeException("Product update failed");
                }

                OrderItemDTO oiDto2 = new OrderItemDTO();
                oiDto2.setSeq(seq);
                int orderItemFlag = orderItemDAO.delete(oiDto2);
                if (orderItemFlag != 0) {
                    throw new RuntimeException("OrderItem delete failed");
                }
            }

            OrderDTO oDto = new OrderDTO();
            oDto.setOrderId(Integer.parseInt(orderId));
            int orderFlag = orderDAO.delete(oDto);
            if (orderFlag != 0) {
                throw new RuntimeException("Order delete failed");
            }

        } catch (Exception e) {
            throw new RuntimeException("Transaction failed: " + e.getMessage(), e);
        }
    }

}