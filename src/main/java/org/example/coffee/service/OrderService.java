package org.example.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderItemDAO orderItemDAO;
    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public void deleteOrder(String orderId) {

        // 1. order_item 테이블에서 상품의 수량을 받아서 상품의 수량 수정 후 주문 상품 삭제
        // order_item 테이블에서 order_id = #{order_id}인 객체들을 가져옴
        // ArrayList<OrderItemDTO> dto ...
        // 해당 리스트를 루프 돌려서 각 주문 상품(OrderItemDTO)의 product_id와 count, seq를 추출
        // product_id를 이용해서 해당 상품을 찾고 그 상품의 quantity를 count만큼 증가시킨다.
        // 그리고 받아 놓은 order_item 테이블의 기본키 값(seq)을 이용해서 주문상품을 delete 시킨다.
        //
        // 2. order_id를 이용해서 orders 테이블의 주문 삭제
        // 이 모든 과정이 한 단위로 이루어 져야함.

        try {
            OrderItemDTO OiDto = new OrderItemDTO();
            OiDto.setOrderId(Integer.parseInt(orderId));

            // dto 안에 order_id를 담아서 order_id=#{order_id}인 객체들을 리스트로 넘김
            ArrayList<OrderItemDTO> orderItemList = orderItemDAO.getOrderItems(OiDto);

            for (OrderItemDTO dto : orderItemList) {

                int productId = dto.getProductId();
                int count = dto.getCount();
                int seq = dto.getSeq();

                // product 테이블 update(product_id, count)
                ProductDTO pDto = new ProductDTO();
                pDto.setProductId(productId);
                pDto.setQuantity(count);

                int productFlag = productDAO.update(pDto); // update 성공하면 0
                if (productFlag != 0) {
                    throw new RuntimeException("Product update failed");
                }

                // order_item 테이블 delete(seq)
                OrderItemDTO oiDto2 = new OrderItemDTO();
                oiDto2.setSeq(seq);
                int orderItemFlag = orderItemDAO.delete(oiDto2);
                if (orderItemFlag != 0) {
                    throw new RuntimeException("OrderItem delete failed");
                }
            }

            // orders 테이블 delete(order_id)
            // 마지막으로 orders 테이블에 있는 주문을 삭제.
            OrderDTO oDto = new OrderDTO();
            oDto.setOrderId(Integer.parseInt(orderId));
            int orderFlag = orderDAO.delete(oDto); // delete 성공하면 0
            if (orderFlag != 0) {
                throw new RuntimeException("Order delete failed");
            }

        } catch (Exception e) {
            throw new RuntimeException("Transaction failed: " + e.getMessage(), e);
        }
    }

}