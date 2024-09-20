package msa.ecommerce.order_line.repository;

import msa.ecommerce.order_line.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
