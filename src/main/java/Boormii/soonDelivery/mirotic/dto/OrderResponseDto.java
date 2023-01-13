package Boormii.soonDelivery.mirotic.dto;

import Boormii.soonDelivery.mirotic.domain.Mirotic;
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

    public static OrderResponseDto registerOrder(Mirotic mirotic){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.title = mirotic.getTitle();
        orderResponseDto.storeName = mirotic.getStoreName();
        orderResponseDto.deliveryAddress = mirotic.getDeliveryAddress();
        orderResponseDto.totalPrice = mirotic.getTotalPrice();
        orderResponseDto.deliveryPay = mirotic.getDeliveryPay();
        orderResponseDto.requirement = mirotic.getRequirement();

        return orderResponseDto;
    }

}
