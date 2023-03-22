package Boormii.soonDelivery.orders.dto;

import Boormii.soonDelivery.orders.domain.DeliveryStatus;
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
    private String nickName;

    @NotNull
    private String storeName;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private Integer totalPrice;

    @NotNull
    private Integer deliveryPay;

    private DeliveryStatus deliveryStatus;

    private String requirement;

    private int flag;
    public static OrdersResponseDto registerOrder(Orders orders, int flag){
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto();
        ordersResponseDto.title = orders.getTitle();
        ordersResponseDto.nickName = orders.getMembers().getNickName();
        ordersResponseDto.storeName = orders.getStoreName();
        ordersResponseDto.deliveryAddress = orders.getDeliveryAddress();
        ordersResponseDto.totalPrice = orders.getTotalPrice();
        ordersResponseDto.deliveryPay = orders.getDeliveryPay();
        ordersResponseDto.requirement = orders.getRequirement();
        ordersResponseDto.deliveryStatus = orders.getDeliveryStatus();
        ordersResponseDto.flag = flag;

        return ordersResponseDto;
    }

}
