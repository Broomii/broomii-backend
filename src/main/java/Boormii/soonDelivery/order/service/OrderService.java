package Boormii.soonDelivery.order.service;

import Boormii.soonDelivery.order.domain.Order;
import Boormii.soonDelivery.order.dto.OrderResponseDto;
import Boormii.soonDelivery.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto createOrder(Order order){
        orderRepository.save(order);
        return OrderResponseDto.registerOrder(order);
    }
}
