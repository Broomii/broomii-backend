package Boormii.soonDelivery.orders.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrdersEditRequestDto {
    @NotNull
    private Long id;

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
}
