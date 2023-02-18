package Boormii.soonDelivery.orders.service;

import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.dto.OrdersCreateRequestDto;
import Boormii.soonDelivery.orders.dto.OrdersEditRequestDto;
import Boormii.soonDelivery.orders.dto.OrdersListResponseDto;
import Boormii.soonDelivery.orders.dto.OrdersResponseDto;
import Boormii.soonDelivery.orders.repository.OrdersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final MembersRepository membersRepository;

    @Transactional
    public Long createOrder(OrdersCreateRequestDto ordersCreateRequestDto, String email){
        Orders orders  = Orders.createOrder(ordersCreateRequestDto, membersRepository.findByEmail(email).get().getNickName());
        ordersRepository.save(orders);
        return orders.getId();
//      return OrdersResponseDto.registerOrder(orders);
    }

    @Transactional
    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    @Transactional
    public OrdersResponseDto getOrder(Long id) {
        return OrdersResponseDto.registerOrder(ordersRepository.findById(id).get());
    }

    @Transactional
    public List<OrdersListResponseDto> getOrderList() {
        List<OrdersListResponseDto> ordersList = new ArrayList<>();

        for (Orders orders :ordersRepository.findAll()) {
            ordersList.add(OrdersListResponseDto.getOrdersList(orders));
        }

        return ordersList;
    }

    @Transactional
    public Long editOrder(OrdersEditRequestDto ordersEditRequestDto) {
        Optional<Orders> editedOrder = ordersRepository.findById(ordersEditRequestDto.getId());

        return editedOrder.get().editOrder(ordersEditRequestDto);
    }
}
