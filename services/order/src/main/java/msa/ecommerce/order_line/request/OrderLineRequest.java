package msa.ecommerce.order_line.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
        Integer id,

        Integer order_id,

        Integer product_id,

        double quantity
) {
}
