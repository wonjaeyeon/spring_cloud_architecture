package msa.ecommerce.order.request;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import msa.ecommerce.order.entity.PaymentMethod;
import msa.ecommerce.product.request.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

public record OrderRequest(

        Integer order_id,

        @NotNull
        String reference,

        @NotNull
        @Positive(message = "Total amount should be positive")
        BigDecimal total_amount,


        //NOTE : message 빼먹지 말기
        @NotNull(message = "Payment method should be provided")
        @Enumerated(STRING)
        PaymentMethod payment_method,

        @NotNull(message = "Customer id should be present")
        String customer_id,

        // NOTE : purchase request가 들어가야 한다.
         @NotEmpty(message = "You should provide at least one product")
        List<PurchaseRequest> products
) {
}
