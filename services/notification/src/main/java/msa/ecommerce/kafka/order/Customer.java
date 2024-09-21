package msa.ecommerce.kafka.order;

public record Customer(
        String id,
        String first_name,
        String last_name,
        String email

) {
}
