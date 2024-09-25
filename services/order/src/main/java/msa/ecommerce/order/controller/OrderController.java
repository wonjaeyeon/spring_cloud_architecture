package msa.ecommerce.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import msa.ecommerce.order.request.OrderRequest;
import msa.ecommerce.order.request.OrderResponse;
import msa.ecommerce.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor // Lombok 이 생성자를 자동으로 생성해줌 -> 일일히 생성자 선언을 하지 않아도 됨
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        // get all orders
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer order_id) {
        // get order by id
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(order_id));
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        // create order
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }

    @PutMapping
    public void updateOrder(@RequestBody @Valid OrderRequest orderRequest) {
        orderService.updateOrder(orderRequest);
    }

    @DeleteMapping("/{order_id}")
    public void deleteOrder(@PathVariable Integer order_id) {
        orderService.deleteOrder(order_id);
    }
}
