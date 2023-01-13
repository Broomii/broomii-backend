package Boormii.soonDelivery.order.dto;

import Boormii.soonDelivery.order.domain.Order;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
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

    public static OrderResponseDto registerOrder(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.title = order.getTitle();
        orderResponseDto.storeName = order.getStoreName();
        orderResponseDto.deliveryAddress = order.getDeliveryAddress();
        orderResponseDto.totalPrice = order.getTotalPrice();
        orderResponseDto.deliveryPay = order.getDeliveryPay();
        orderResponseDto.requirement = order.getRequirement();

        return orderResponseDto;
    }

}
