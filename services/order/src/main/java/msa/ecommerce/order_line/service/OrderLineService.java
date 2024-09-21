package msa.ecommerce.order_line.service;

import lombok.RequiredArgsConstructor;
import msa.ecommerce.order_line.repository.OrderLineRepository;
import msa.ecommerce.order_line.request.OrderLineRequest;
import msa.ecommerce.order_line.request.OrderLineResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findByOrderId(Integer orderId) {
        return orderLineRepository.findByOrderId(orderId).stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
