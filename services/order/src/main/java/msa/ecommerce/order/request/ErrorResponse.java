package msa.ecommerce.order.request;

import java.util.HashMap;

public record ErrorResponse(HashMap<String, String> errors) {
}
