package msa.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PurchaseResponse(
        @NotNull(message = "Product id cannot be null")
        Integer product_id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
