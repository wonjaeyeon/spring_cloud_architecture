package msa.ecommerce.notification.consumer;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.ecommerce.email.EmailService;
import msa.ecommerce.kafka.order.OrderConfirmation;
import msa.ecommerce.kafka.payment.PaymentConfirmation;
import msa.ecommerce.notification.entity.Notification;
import msa.ecommerce.notification.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static msa.ecommerce.notification.entity.NotificationType.ORDER_CONFIRMATION;
import static msa.ecommerce.notification.entity.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consumed payment confirmation -> %s", paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .createdDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());

        // send email
        var customerName = paymentConfirmation.customer_first_name() + " " + paymentConfirmation.customer_last_name();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customer_email(),
                customerName,
                paymentConfirmation.total_amount(),
                paymentConfirmation.order_reference());

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consumed order confirmation -> %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .createdDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build());

        // send email
        var customerName = orderConfirmation.customer().first_name() + " " + orderConfirmation.customer().last_name();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.total_amount(),
                orderConfirmation.order_reference(),
                orderConfirmation.products()
                );
    }
}
