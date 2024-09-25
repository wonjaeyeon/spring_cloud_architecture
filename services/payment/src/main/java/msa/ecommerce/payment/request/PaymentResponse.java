package msa.ecommerce.payment.request;

import jakarta.persistence.Enumerated;
import msa.ecommerce.payment.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

public record PaymentResponse(
        Integer payment_id,
        BigDecimal payment_amount,
        @Enumerated(STRING)
        PaymentMethod payment_method,
        Integer order_id,
        LocalDateTime created_date,
        LocalDateTime updated_date
) {
}
