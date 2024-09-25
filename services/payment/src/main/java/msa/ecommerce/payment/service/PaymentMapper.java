package msa.ecommerce.payment.service;

import msa.ecommerce.payment.entity.Payment;
import msa.ecommerce.payment.request.PaymentRequest;
import msa.ecommerce.payment.request.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    // toPayment
    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.payment_id())
                .amount(paymentRequest.payment_amount())
                .paymentMethod(paymentRequest.payment_method())
                .orderId(paymentRequest.order_id())
//                .createdDate(paymentRequest.created_date()) # 이게 맞지 않나?
//                .lastModifiedDate(paymentRequest.updated_date())
                .build();
    }

    // toPaymentResponse
    public PaymentResponse toPaymentResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getOrderId(),
                payment.getCreatedDate(),
                payment.getLastModifiedDate()
        );
    }
}
