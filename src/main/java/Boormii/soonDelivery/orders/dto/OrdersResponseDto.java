package Boormii.soonDelivery.orders.dto;

import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseDto {
    @NotNull
    private String title;

    @NotNull
    private String storeName;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private Integer totalPrice;

    @NotNull
    private Integer deliveryPay;

    private String requirement;

    public static OrdersResponseDto registerOrder(Orders orders){
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto();
        ordersResponseDto.title = orders.getTitle();
        ordersResponseDto.storeName = orders.getStoreName();
        ordersResponseDto.deliveryAddress = orders.getDeliveryAddress();
        ordersResponseDto.totalPrice = orders.getTotalPrice();
        ordersResponseDto.deliveryPay = orders.getDeliveryPay();
        ordersResponseDto.requirement = orders.getRequirement();

        return ordersResponseDto;
    }

}