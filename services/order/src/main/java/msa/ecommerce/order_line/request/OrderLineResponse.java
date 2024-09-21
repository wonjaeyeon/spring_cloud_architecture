package msa.ecommerce.order_line.request;

public record OrderLineResponse(
        Integer order_line_id,
        double quantity
) {
}
