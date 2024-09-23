package msa.ecommerce.order_line.controller;

import lombok.RequiredArgsConstructor;
import msa.ecommerce.order_line.request.OrderLineResponse;
import msa.ecommerce.order_line.service.OrderLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order_lines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{order_id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order_id") Integer order_id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderLineService.findByOrderId(order_id));
    }
}
