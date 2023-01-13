package Boormii.soonDelivery.mirotic.domain;

import Boormii.soonDelivery.mirotic.dto.OrderRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mirotic {


    @Id
    @GeneratedValue
    @Column(name="id")
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

    public static Mirotic createOrder(OrderRequestDto orderRequestDto){
        Mirotic mirotic = new Mirotic();
        mirotic.storeName = orderRequestDto.getStoreName();
        mirotic.title = orderRequestDto.getTitle();
        mirotic.deliveryAddress = orderRequestDto.getDeliveryAddress();
        mirotic.totalPrice = orderRequestDto.getTotalPrice();
        mirotic.deliveryPay = orderRequestDto.getDeliveryPay();
        mirotic.requirement = orderRequestDto.getRequirement();

        return mirotic;
    }

}
