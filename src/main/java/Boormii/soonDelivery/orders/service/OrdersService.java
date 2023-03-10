package Boormii.soonDelivery.orders.service;

import Boormii.soonDelivery.global.exception.ApiException;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.dto.*;
import Boormii.soonDelivery.orders.repository.OrdersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final MembersRepository membersRepository;
    private final int sameUser =  1;
    private final int differentUser = 0;

    @Transactional
    public Long createOrder(OrdersCreateRequestDto ordersCreateRequestDto, String email) {
        Orders orders = Orders.createOrder(ordersCreateRequestDto, membersRepository.findByEmail(email).get().getNickName());
        ordersRepository.save(orders);
        return orders.getId();
//      return OrdersResponseDto.registerOrder(orders);
    }

    @Transactional
    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    @Transactional
    public OrdersResponseDto getOrder(Long id, String email) {
        Orders orders = ordersRepository.findById(id).get();
        Members members = membersRepository.findByEmail(email).get();
        int flag = differentUser;
        if (orders.getNickName().equals(members.getNickName())){
            flag = sameUser;
        }
        return OrdersResponseDto.registerOrder(orders, flag);
    }

    @Transactional
    public List<OrdersListResponseDto> getOrderList() {
        List<OrdersListResponseDto> ordersList = new ArrayList<>();
        for (Orders orders : ordersRepository.findAll()) {
            ordersList.add(OrdersListResponseDto.getOrdersList(orders));
        }
        if (ordersList == null) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "??? ????????? ???????????? ????????????.");
        }

        return ordersList;
    }

    @Transactional
    public Long editOrder(OrdersEditRequestDto ordersEditRequestDto) {
        Optional<Orders> editedOrder = ordersRepository.findById(ordersEditRequestDto.getId());

        return editedOrder.get().editOrder(ordersEditRequestDto);
    }

    @Transactional
    public Long editDeliveryStatus(DeliveryStatusEditRequestDto deliveryStatusEditRequestDto) {
        Optional<Orders> orders = ordersRepository.findById(deliveryStatusEditRequestDto.getId());
        return orders.get().editDeliveryStatus(deliveryStatusEditRequestDto.getDeliveryStatus());
    }
}
