package Boormii.soonDelivery.order.controller;

import Boormii.soonDelivery.order.domain.Order;
import Boormii.soonDelivery.order.dto.OrderRequestDto;
import Boormii.soonDelivery.order.dto.OrderResponseDto;
import Boormii.soonDelivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/create")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto)
    {
        Order order = Order.createOrder(orderRequestDto);
        return orderService.createOrder(order);
    }
}
