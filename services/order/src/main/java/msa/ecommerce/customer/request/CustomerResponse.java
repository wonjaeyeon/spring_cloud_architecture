package msa.ecommerce.customer.request;

public record CustomerResponse (
        String customer_id,
        String first_name,
        String last_name,
        String email,
        String phone
){
}
