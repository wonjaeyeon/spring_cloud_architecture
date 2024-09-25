package msa.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product Id is required")
        Integer product_id,
        @NotNull(message = "Product Quantity is required")
        @Positive(message = "Product Quantity must be positive")
        double quantity
) {
}
