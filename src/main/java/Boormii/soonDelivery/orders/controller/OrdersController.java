package Boormii.soonDelivery.orders.controller;

import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.dto.DeliveryStatusEditRequestDto;
import Boormii.soonDelivery.orders.dto.OrdersCreateRequestDto;
import Boormii.soonDelivery.orders.dto.OrdersEditRequestDto;
import Boormii.soonDelivery.orders.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    private final ResponseService responseService;
    private final JwtUtils jwtUtils;
    @PostMapping("orders/create")
    public CommonResponse<Object> createOrder(@RequestBody OrdersCreateRequestDto ordersCreateRequestDto, HttpServletRequest http)
    {
        return responseService.getSuccessResponse("주문 생성 완료", ordersService.createOrder(ordersCreateRequestDto, jwtUtils.getEmailFromRequestHeader(http)));
    }

    @DeleteMapping("orders/delete/{id}")
    public CommonResponse<Object> deleteOrder(@PathVariable("id") Long id)
    {
        ordersService.deleteOrder(id);
        return responseService.getSuccessResponse("주문 삭제 완료", null);
    }

    @GetMapping("orders/get/{id}")
    public CommonResponse<Object> getOrder(@PathVariable("id") Long id, HttpServletRequest http)
    {
        return responseService.getSuccessResponse("주문 조회 완료 ", ordersService.getOrder(id, jwtUtils.getEmailFromRequestHeader(http)));
    }

    @GetMapping("orders/getOrderList")
    public CommonResponse<Object> getOrderList()
    {
        return responseService.getSuccessResponse("주문 목록 반환 완료", ordersService.getOrderList());
    }

    @PutMapping("orders/edit")
    public CommonResponse<Object> editOrder(@RequestBody OrdersEditRequestDto ordersEditRequestDto)
    {
        return responseService.getSuccessResponse("주문 수정 성공", ordersService.editOrder(ordersEditRequestDto));
    }

    @PutMapping("orders/editDeliveryStatus")
    public CommonResponse<Object> editDeliveryStatus(@RequestBody DeliveryStatusEditRequestDto deliveryStatusEditRequestDto)
    {
        return responseService.getSuccessResponse("주문 상태 수정 성공", ordersService.editDeliveryStatus(deliveryStatusEditRequestDto));
    }
}
