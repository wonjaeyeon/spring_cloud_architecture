package msa.ecommerce.exception;

public class OrderLineNotfoundException extends RuntimeException {
    public OrderLineNotfoundException(String message) {
        super(message);
    }
}
