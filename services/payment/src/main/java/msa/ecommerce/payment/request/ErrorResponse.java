package msa.ecommerce.payment.request;

import java.util.HashMap;

public record ErrorResponse(
        HashMap<String, String> message
) {
}
