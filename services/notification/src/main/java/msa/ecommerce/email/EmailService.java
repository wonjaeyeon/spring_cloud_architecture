package msa.ecommerce.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.ecommerce.kafka.order.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.context.Context;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static msa.ecommerce.email.entity.EmailTemplate.ORDER_CONFIRMATION;
import static msa.ecommerce.email.entity.EmailTemplate.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {


    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destination_email,
            String customer_name,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED,
                        UTF_8.name());
        messageHelper.setFrom("contact@woncoding.com");
        messageHelper.setTo(destination_email);

        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customer_name);
        System.out.println("customerName: " + customer_name);

        variables.put("amount", amount);
        System.out.println("amount: " + amount);

        variables.put("orderReference", orderReference);
        System.out.println("orderReference: " + orderReference);

        Context context = new Context(); // context from Thymeleaf
        context.setVariables(variables);
        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = springTemplateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destination_email);
            javaMailSender.send(mimeMessage);
            log.info("Email sent successfully to {}", destination_email);
        } catch (MessagingException e) {
            log.warn("WARNING : Cannot send email to {}", destination_email);

        }
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED,
                        UTF_8.name());
        messageHelper.setFrom("contact@woncoding.com");
        messageHelper.setTo(destinationEmail);

        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();

        variables.put("customerName", customerName);
        System.out.println("customerName: " + customerName);

        variables.put("total_amount", amount);
        System.out.println("total_amount: " + amount);

        variables.put("orderReference", orderReference);
        System.out.println("orderReference: " + orderReference);

        variables.put("products", products);
        System.out.println("products: " + products);

        Context context = new Context(); // context from Thymeleaf
        context.setVariables(variables);
        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try{
            String htmlTemplate = springTemplateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info("Email sent successfully to {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("WARNING : Cannot send email to {}", destinationEmail);

        }
    }
}
