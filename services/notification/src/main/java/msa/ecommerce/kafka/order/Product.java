package msa.ecommerce.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer product_id,
        String product_name,
        String description,
        BigDecimal price,
        double quantity
) {
}
