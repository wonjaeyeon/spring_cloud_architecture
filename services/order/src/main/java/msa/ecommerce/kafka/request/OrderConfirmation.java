package msa.ecommerce.kafka.request;

import msa.ecommerce.customer.request.CustomerResponse;
import msa.ecommerce.order.entity.PaymentMethod;
import msa.ecommerce.product.request.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
