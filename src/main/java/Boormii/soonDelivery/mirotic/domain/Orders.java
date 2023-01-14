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
public class Orders {


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

    public static Orders createOrder(OrderRequestDto orderRequestDto){
        Orders orders = new Orders();
        orders.storeName = orderRequestDto.getStoreName();
        orders.title = orderRequestDto.getTitle();
        orders.deliveryAddress = orderRequestDto.getDeliveryAddress();
        orders.totalPrice = orderRequestDto.getTotalPrice();
        orders.deliveryPay = orderRequestDto.getDeliveryPay();
        orders.requirement = orderRequestDto.getRequirement();

        return orders;
    }

}
