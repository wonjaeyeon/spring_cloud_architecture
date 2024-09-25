package msa.ecommerce.notification;

import msa.ecommerce.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String order_reference,
        BigDecimal payment_amount,
        PaymentMethod payment_method,

        String customer_first_name,
        String customer_last_name,
        String customer_email

) {
}
