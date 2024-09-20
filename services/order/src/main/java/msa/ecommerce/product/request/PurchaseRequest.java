package msa.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product id is required")
        Integer product_id,
        @Positive(message = "Quantity should be positive")
        Integer quantity
) {
}
