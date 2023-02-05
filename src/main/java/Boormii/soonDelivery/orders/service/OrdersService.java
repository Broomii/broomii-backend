package Boormii.soonDelivery.orders.service;

import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.dto.OrdersResponseDto;
import Boormii.soonDelivery.orders.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Transactional
    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    @Transactional
    public OrdersResponseDto createOrder(Orders orders){
        ordersRepository.save(orders);
        return OrdersResponseDto.registerOrder(orders);
    }
}
