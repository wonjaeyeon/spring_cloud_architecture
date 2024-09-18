package msa.ecommerce.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (

        Integer id,
        @NotNull(message = "Product Name is required")
        String name,
        @NotNull(message = "Product Description is required")
        String description,
        @Positive(message = "Product Quantity must be positive")
        double availableQuantity,
        @Positive(message = "Product Price must be positive")
        BigDecimal price,
        @NotNull(message = "Category Id is required")
        Integer categoryId
){
}
