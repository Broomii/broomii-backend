package Boormii.soonDelivery.orders.domain;

import Boormii.soonDelivery.chat.domain.ChattingMessage;
import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.orders.dto.OrdersCreateRequestDto;
import Boormii.soonDelivery.orders.dto.OrdersEditRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    private DeliveryStatus deliveryStatus;

    private String requirement;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    @Nullable
    private Members members;

    @OneToMany(mappedBy = "orders")
    private List<ChattingRoom> chattingRoomList = new ArrayList<>();

//    @ManyToOne
//    private Members member;

    public static Orders createOrder(OrdersCreateRequestDto ordersCreateRequestDto, Members orderMan){
        Orders orders = new Orders();
        orders.storeName = ordersCreateRequestDto.getStoreName();
        orders.title = ordersCreateRequestDto.getTitle();
        orders.deliveryAddress = ordersCreateRequestDto.getDeliveryAddress();
        orders.totalPrice = ordersCreateRequestDto.getTotalPrice();
        orders.deliveryPay = ordersCreateRequestDto.getDeliveryPay();
        orders.requirement = ordersCreateRequestDto.getRequirement();
        orders.deliveryStatus = DeliveryStatus.deliverable;
        orders.members = orderMan;

        return orders;
    }

    public Long editOrder(OrdersEditRequestDto ordersEditRequestDto){
        this.storeName = ordersEditRequestDto.getStoreName();
        this.title = ordersEditRequestDto.getTitle();
        this.deliveryAddress = ordersEditRequestDto.getDeliveryAddress();
        this.totalPrice = ordersEditRequestDto.getTotalPrice();
        this.deliveryPay = ordersEditRequestDto.getDeliveryPay();
        this.requirement = ordersEditRequestDto.getRequirement();
        this.deliveryStatus = DeliveryStatus.deliverable;

        return this.getId();
    }

    public Long editDeliveryStatus(String deliveryStatus){
        this.deliveryStatus = DeliveryStatus.valueOf(deliveryStatus);

        return this.id;
    }

    public void disconnectMembers() {
        this.members = null;
    }

    public void addChattingRoom(ChattingRoom chattingRoom) {
        this.chattingRoomList.add(chattingRoom);
    }

}
