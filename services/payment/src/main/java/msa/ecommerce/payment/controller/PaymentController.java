package msa.ecommerce.payment.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import msa.ecommerce.payment.request.PaymentRequest;
import msa.ecommerce.payment.request.PaymentResponse;
import msa.ecommerce.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{payment_id}")
    public ResponseEntity<PaymentResponse> findById(@PathVariable("payment_id") Integer paymentId) {
        return ResponseEntity.ok(paymentService.findById(paymentId));
    }


    @PostMapping
    public ResponseEntity<Integer> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }

    @PutMapping
    public ResponseEntity<PaymentResponse> updatePayment( @RequestBody @Valid PaymentRequest paymentRequest) {
        paymentService.updatePayment(paymentRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping
    public ResponseEntity<Integer> deletePayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.deletePayment(paymentRequest);
        return ResponseEntity.accepted().build();
    }

}
