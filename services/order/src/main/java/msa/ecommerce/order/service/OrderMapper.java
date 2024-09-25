package msa.ecommerce.order.service;

import msa.ecommerce.order.entity.Order;
import msa.ecommerce.order.request.OrderRequest;
import msa.ecommerce.order.request.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.order_id())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.total_amount())
                .paymentMethod(orderRequest.payment_method())
                .customerId(orderRequest.customer_id())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}