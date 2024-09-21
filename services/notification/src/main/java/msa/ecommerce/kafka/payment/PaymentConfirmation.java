package msa.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String order_reference,
        BigDecimal total_amount,
        PaymentMethod payment_method,

        String customer_first_name,
        String customer_last_name,
        String customer_email
) {
}
