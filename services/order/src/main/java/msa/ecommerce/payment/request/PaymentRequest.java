package msa.ecommerce.payment.request;

import msa.ecommerce.customer.request.CustomerResponse;
import msa.ecommerce.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(


        //@NotNull
        BigDecimal payment_amount,
        //@Enumerated(STRING)
        PaymentMethod payment_method,
        //@NotNull
        Integer order_id,
        String order_reference,
        CustomerResponse customer
) {
}
