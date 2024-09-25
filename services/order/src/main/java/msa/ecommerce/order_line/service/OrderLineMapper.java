package msa.ecommerce.order_line.service;

import msa.ecommerce.order.entity.Order;
import msa.ecommerce.order_line.entity.OrderLine;
import msa.ecommerce.order_line.request.OrderLineRequest;
import msa.ecommerce.order_line.request.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.order_id())
                                .build())
                .productId(orderLineRequest.product_id())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
