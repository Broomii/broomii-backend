package Boormii.soonDelivery.orders.controller;

import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.dto.OrdersRequestDto;
import Boormii.soonDelivery.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    private final ResponseService responseService;

    @PostMapping("orders/create")
    public CommonResponse<Object> createOrder(@RequestBody OrdersRequestDto ordersRequestDto)
    {
        Orders orders = Orders.createOrder(ordersRequestDto);
        return responseService.getSuccessResponse("주문 생성 완료", ordersService.createOrder(orders));
    }

    @DeleteMapping("orders/delete/{id}")
    public CommonResponse<Object> deleteOrder(@PathVariable("id") Long id) {
        ordersService.deleteOrder(id);
        return responseService.getSuccessResponse("주문 삭제 완료", null);
    }
}
