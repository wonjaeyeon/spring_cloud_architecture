package msa.ecommerce.order_line.repository;

import msa.ecommerce.order_line.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findByOrderId(Integer orderId);
}
