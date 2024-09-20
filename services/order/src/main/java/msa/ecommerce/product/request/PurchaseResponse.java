package msa.ecommerce.product.request;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer product_id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
