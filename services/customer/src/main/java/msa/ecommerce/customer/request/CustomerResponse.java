package msa.ecommerce.customer.request;

import msa.ecommerce.customer.entity.Address;

public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
){
}
