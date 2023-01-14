package Boormii.soonDelivery.mirotic.dto;

import Boormii.soonDelivery.mirotic.domain.Orders;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public static OrderResponseDto registerOrder(Orders orders){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.title = orders.getTitle();
        orderResponseDto.storeName = orders.getStoreName();
        orderResponseDto.deliveryAddress = orders.getDeliveryAddress();
        orderResponseDto.totalPrice = orders.getTotalPrice();
        orderResponseDto.deliveryPay = orders.getDeliveryPay();
        orderResponseDto.requirement = orders.getRequirement();

        return orderResponseDto;
    }

}
