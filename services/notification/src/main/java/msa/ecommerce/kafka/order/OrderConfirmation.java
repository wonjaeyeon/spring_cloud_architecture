package msa.ecommerce.kafka.order;

import msa.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String order_reference,
        BigDecimal total_amount,
        PaymentMethod payment_method,
        Customer customer,
        List<Product> products
) {
}
