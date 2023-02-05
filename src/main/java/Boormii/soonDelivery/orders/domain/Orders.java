package Boormii.soonDelivery.orders.domain;

import Boormii.soonDelivery.orders.dto.OrdersRequestDto;
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


    public static Orders createOrder(OrdersRequestDto ordersRequestDto){
        Orders orders = new Orders();
        orders.storeName = ordersRequestDto.getStoreName();
        orders.title = ordersRequestDto.getTitle();
        orders.deliveryAddress = ordersRequestDto.getDeliveryAddress();
        orders.totalPrice = ordersRequestDto.getTotalPrice();
        orders.deliveryPay = ordersRequestDto.getDeliveryPay();
        orders.requirement = ordersRequestDto.getRequirement();
        orders.state = OrderState.deliverable;

        return orders;
    }

}
