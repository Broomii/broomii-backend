package Boormii.soonDelivery.orders.service;

import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.dto.OrdersEditRequestDto;
import Boormii.soonDelivery.orders.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Transactional
    public Long createOrder(Orders orders){
        ordersRepository.save(orders);
        return orders.getId();
//      return OrdersResponseDto.registerOrder(orders);
    }

    @Transactional
    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    @Transactional
    public Optional<Orders> getOrder(Long id) {
        return ordersRepository.findById(id);
    }

    @Transactional
    public List<Orders> getOrderList() {
        return ordersRepository.findAll();
    }

    @Transactional
    public Long editOrder(OrdersEditRequestDto ordersEditRequestDto) {
        Optional<Orders> editedOrder = ordersRepository.findById(ordersEditRequestDto.getId());

        return editedOrder.get().editOrder(ordersEditRequestDto);
    }
}
