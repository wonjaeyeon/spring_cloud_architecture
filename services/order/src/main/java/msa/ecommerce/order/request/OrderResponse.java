package msa.ecommerce.order.request;

import msa.ecommerce.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse (
        Integer order_id,
        String reference,
        BigDecimal total_amount,
        PaymentMethod payment_method,
        String customer_id
) {
}