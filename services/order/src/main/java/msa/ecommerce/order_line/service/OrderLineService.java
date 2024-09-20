package msa.ecommerce.order_line.service;

import lombok.RequiredArgsConstructor;
import msa.ecommerce.order_line.repository.OrderLineRepository;
import msa.ecommerce.order_line.request.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
