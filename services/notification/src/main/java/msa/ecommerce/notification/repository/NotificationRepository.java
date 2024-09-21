package msa.ecommerce.notification.repository;

import msa.ecommerce.kafka.payment.PaymentConfirmation;
import msa.ecommerce.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

// 왜 PaymentConfirmation을 사용하는지 모르겠음 -> youtuber's mistake
public interface NotificationRepository extends MongoRepository<Notification, String> {
}