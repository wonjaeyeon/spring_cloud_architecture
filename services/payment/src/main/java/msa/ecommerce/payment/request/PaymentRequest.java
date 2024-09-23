package msa.ecommerce.payment.request;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import msa.ecommerce.payment.entity.PaymentMethod;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

public record PaymentRequest(

        Integer payment_id,
        //@NotNull
        BigDecimal payment_amount,
        //@Enumerated(STRING)
        PaymentMethod payment_method,
        //@NotNull
        Integer order_id,
        String order_reference,
        Customer customer
//        @CreatedDate
//        @NotNull
//        LocalDateTime created_date,
//        @LastModifiedDate
//        @NotNull
//        LocalDateTime updated_date

) {
}
