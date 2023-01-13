package Boormii.soonDelivery.mirotic.controller;

import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.mirotic.domain.Mirotic;
import Boormii.soonDelivery.mirotic.dto.OrderRequestDto;
import Boormii.soonDelivery.mirotic.dto.OrderResponseDto;
import Boormii.soonDelivery.mirotic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ResponseService responseService;

    @PostMapping("order/create")
    public CommonResponse<Object> createOrder(@RequestBody OrderRequestDto orderRequestDto)
    {
        Mirotic mirotic = Mirotic.createOrder(orderRequestDto);
        return responseService.getSuccessResponse("주문 생성 완료", orderService.createOrder(mirotic));
    }
}
