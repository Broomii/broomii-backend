package Boormii.soonDelivery.orders.dto;

import Boormii.soonDelivery.orders.domain.DeliveryStatus;
import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class OrdersListResponseDto {

    @NotNull
    private String title;

    @NotNull
    private String nickName;

    @NotNull
    private Long id;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private Integer deliveryPay;

    @NotNull
    private DeliveryStatus deliveryStatus;

    public static OrdersListResponseDto getOrdersList(Orders orders) {
        OrdersListResponseDto ordersListResponseDto = new OrdersListResponseDto();
        ordersListResponseDto.title = orders.getTitle();
        ordersListResponseDto.nickName = orders.getNickName();
        ordersListResponseDto.id = orders.getId();
        ordersListResponseDto.deliveryAddress = orders.getDeliveryAddress();
        ordersListResponseDto.deliveryStatus = orders.getDeliveryStatus();
        ordersListResponseDto.deliveryPay = orders.getDeliveryPay();

        return ordersListResponseDto;
    }
}
