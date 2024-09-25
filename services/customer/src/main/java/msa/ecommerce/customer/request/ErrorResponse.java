package msa.ecommerce.customer.request;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
){

}