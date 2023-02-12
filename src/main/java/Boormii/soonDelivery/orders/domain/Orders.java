package Boormii.soonDelivery.orders.domain;

import Boormii.soonDelivery.orders.dto.OrdersCreateRequestDto;
import Boormii.soonDelivery.orders.dto.OrdersEditRequestDto;
import jakarta.persistence.*;
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
    private String email;
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

    private OrderState state;

    private String requirement;

//    @ManyToOne
//    private Members member;

    public static Orders createOrder(OrdersCreateRequestDto ordersCreateRequestDto, String email){
        Orders orders = new Orders();
        orders.storeName = ordersCreateRequestDto.getStoreName();
        orders.title = ordersCreateRequestDto.getTitle();
        orders.deliveryAddress = ordersCreateRequestDto.getDeliveryAddress();
        orders.totalPrice = ordersCreateRequestDto.getTotalPrice();
        orders.deliveryPay = ordersCreateRequestDto.getDeliveryPay();
        orders.requirement = ordersCreateRequestDto.getRequirement();
        orders.email = email;
        orders.state = OrderState.deliverable;

        return orders;
    }

    public Long editOrder(OrdersEditRequestDto ordersEditRequestDto){
        this.storeName = ordersEditRequestDto.getStoreName();
        this.title = ordersEditRequestDto.getTitle();
        this.deliveryAddress = ordersEditRequestDto.getDeliveryAddress();
        this.totalPrice = ordersEditRequestDto.getTotalPrice();
        this.deliveryPay = ordersEditRequestDto.getDeliveryPay();
        this.requirement = ordersEditRequestDto.getRequirement();
        this.state = OrderState.deliverable;

        return this.getId();
    }

}
