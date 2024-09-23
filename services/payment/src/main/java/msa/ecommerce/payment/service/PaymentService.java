package msa.ecommerce.payment.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import msa.ecommerce.notification.NotificationProducer;
import msa.ecommerce.notification.PaymentNotificationRequest;
import msa.ecommerce.payment.entity.Payment;
import msa.ecommerce.payment.exception.PaymentNotFoundException;
import msa.ecommerce.payment.repository.PaymentRepository;
import msa.ecommerce.payment.request.PaymentRequest;
import msa.ecommerce.payment.request.PaymentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    private final NotificationProducer notificationProducer;

    public List<PaymentResponse> findAll() {
        return paymentRepository.findAll().stream().
                map(paymentMapper::toPaymentResponse)
                .collect(Collectors.toList());
    }

    public PaymentResponse findById(Integer paymentId) {
        return paymentRepository.findById(paymentId)
                .map(paymentMapper::toPaymentResponse)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));
    }

    public Integer createPayment(@Valid PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));

        System.out.println("-------------------------------------------------------");
        System.out.println("paymentRequest.payment_id() = " + paymentRequest.payment_id());
        System.out.println("paymentRequest.payment_amount() = " + paymentRequest.payment_amount());
        System.out.println("paymentRequest.order_reference() = " + paymentRequest.order_reference());
        System.out.println("paymentRequest.payment_method() = " + paymentRequest.payment_method());
        System.out.println("paymentRequest.customer().firstName() = " + paymentRequest.customer().firstName());
        System.out.println("paymentRequest.customer().lastName() = " + paymentRequest.customer().lastName());
        System.out.println("paymentRequest.customer().email() = " + paymentRequest.customer().email());
        System.out.println("-------------------------------------------------------");

        // send message to notification service
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.order_reference(),
                        paymentRequest.payment_amount(),
                        paymentRequest.payment_method(),
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email()
                ));

        return payment.getId();
    }

    public void updatePayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        var payment = paymentRepository.findById(paymentRequest.payment_id())
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentRequest.payment_id()));
        mergePayment(payment, paymentRequest);
        paymentRepository.save(payment);

    }

    private void mergePayment(Payment payment, @Valid PaymentRequest paymentRequest) {
        if (paymentRequest.payment_id() != null) {
            payment.setId(paymentRequest.payment_id());
        }

        if (paymentRequest.payment_amount() != null) {
            payment.setAmount(paymentRequest.payment_amount());
        }

        if (paymentRequest.payment_method() != null) {
            payment.setPaymentMethod(paymentRequest.payment_method());
        }

        if (paymentRequest.order_id() != null) {
            payment.setOrderId(paymentRequest.order_id());
        }

    }

    public void deletePayment(PaymentRequest paymentRequest) {
        if (!paymentRepository.existsById(paymentRequest.payment_id())) {
            throw new PaymentNotFoundException("Payment not found with id: " + paymentRequest.payment_id());
        }
        paymentRepository.deleteById(paymentRequest.payment_id());
    }
}
