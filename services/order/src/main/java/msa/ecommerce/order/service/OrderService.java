package msa.ecommerce.order.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import msa.ecommerce.customer.client.CustomerClient;
import msa.ecommerce.kafka.request.OrderConfirmation;
import msa.ecommerce.kafka.service.OrderProducer;
import msa.ecommerce.order.entity.Order;
import msa.ecommerce.exception.BusinessException;
import msa.ecommerce.exception.OrderNotFoundException;
import msa.ecommerce.order.repository.OrderRepository;
import msa.ecommerce.order.request.OrderRequest;
import msa.ecommerce.order.request.OrderResponse;
import msa.ecommerce.order_line.request.OrderLineRequest;
import msa.ecommerce.order_line.service.OrderLineService;
import msa.ecommerce.payment.client.PaymentClient;
import msa.ecommerce.payment.request.PaymentRequest;
import msa.ecommerce.product.client.ProductClient;
import msa.ecommerce.product.request.PurchaseRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private OrderMapper orderMapper;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public boolean existsById(Integer orderId) {
        return orderRepository.existsById(orderId);
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Order with id %s not found", orderId)
                ));
    }

    public Integer createOrder(OrderRequest orderRequest) {
        // NOTE : 늘 항상 Requirement를 고려하며
        // check the customer --> OpenFeign (customer-ms)
        var customer = this.customerClient.findCustomerById(orderRequest.customer_id())
                .orElseThrow(() -> new BusinessException(
                        String.format("Customer with id %s not found", orderRequest.customer_id())
                ));
        // purchase the products --> product-ms(RestTemplate)
        var purchaseProducts = this.productClient.purchaseProducts(orderRequest.products());

        // persist the order
        Order order = orderMapper.toOrder(orderRequest);
        orderRepository.save(order);

        // persist the order line
        for (PurchaseRequest product : orderRequest.products()) {
            // persist the order line
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            product.product_id(),
                            product.quantity()
                    )
            );
        }

        // TODO : start the payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.total_amount(),
                orderRequest.payment_method(),
                order.getId(),
                order.getReference(),
                customer

        );

        paymentClient.requestOrderPayment(
                paymentRequest
        );


        // send the order confirmation --> notification-ms (Kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.total_amount(),
                        orderRequest.payment_method(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public void updateOrder(@Valid OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderRequest.order_id())
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Order with id %s not found", orderRequest.order_id())
                ));
        mergeOrder(order, orderRequest);
        orderRepository.save(order);
    }

    public void mergeOrder(Order order, OrderRequest orderRequest) {
        if (StringUtils.isNotEmpty(orderRequest.reference())) {
            order.setReference(orderRequest.reference());
        }
        if (orderRequest.total_amount() != null) {
            order.setTotalAmount(orderRequest.total_amount());
        }
        if (orderRequest.payment_method() != null) {
            order.setPaymentMethod(orderRequest.payment_method());
        }
        if (orderRequest.customer_id() != null) {
            order.setCustomerId(orderRequest.customer_id());
        }
    }

    public void deleteOrder(Integer orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException(
                    String.format("Order with id %s not found", orderId)
            );
        }
        orderRepository.deleteById(orderId);
    }
}

