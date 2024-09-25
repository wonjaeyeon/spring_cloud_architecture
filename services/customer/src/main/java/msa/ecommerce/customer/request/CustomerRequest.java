package msa.ecommerce.customer.request;

import jakarta.validation.constraints.NotNull;
import msa.ecommerce.customer.entity.Address;

public record CustomerRequest(
        String id,
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @NotNull(message = "Email is required")
        String email,
        @NotNull(message = "Address is required")
        Address address
) {
}
