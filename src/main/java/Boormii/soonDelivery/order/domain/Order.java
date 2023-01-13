package Boormii.soonDelivery.order.domain;

import Boormii.soonDelivery.order.dto.OrderRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Order {


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

    public static Order createOrder(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.title = orderRequestDto.getTitle();
        order.storeName = orderRequestDto.getStoreName();
        order.deliveryAddress = orderRequestDto.getDeliveryAddress();
        order.totalPrice = orderRequestDto.getTotalPrice();
        order.deliveryPay = orderRequestDto.getDeliveryPay();
        order.requirement = orderRequestDto.getRequirement();

        return order;
    }

}
