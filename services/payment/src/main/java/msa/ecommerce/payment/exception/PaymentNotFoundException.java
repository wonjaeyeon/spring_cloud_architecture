package msa.ecommerce.payment.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String paymentNotFound) {
    }
}
