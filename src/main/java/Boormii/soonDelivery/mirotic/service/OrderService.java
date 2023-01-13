package Boormii.soonDelivery.mirotic.service;

import Boormii.soonDelivery.mirotic.domain.Mirotic;
import Boormii.soonDelivery.mirotic.dto.OrderResponseDto;
import Boormii.soonDelivery.mirotic.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDto createOrder(Mirotic mirotic){
        orderRepository.save(mirotic);
        OrderResponseDto orderResponseDto = OrderResponseDto.registerOrder(orderRepository.findByTitle("d"));
        return orderResponseDto;
    }
}
