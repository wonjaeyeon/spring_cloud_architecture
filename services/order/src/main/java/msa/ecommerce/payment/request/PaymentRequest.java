package msa.ecommerce.payment.request;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import msa.ecommerce.customer.request.CustomerResponse;
import msa.ecommerce.order.entity.PaymentMethod;

import java.math.BigDecimal;

import static jakarta.persistence.EnumType.STRING;

public record PaymentRequest(
        @NotNull
        BigDecimal payment_amount,
        @Enumerated(STRING)
        PaymentMethod payment_method,
        @NotNull
        Integer order_id,
        String order_reference,
        CustomerResponse customer
) {
}
