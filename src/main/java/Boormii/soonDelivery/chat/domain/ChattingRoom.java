package Boormii.soonDelivery.chat.domain;

import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class ChattingRoom {

    @Id
    @GeneratedValue
    @Column(name = "chatting_room_id")
    private Long id;
    @NotNull
    private String deliveryMan;

    @NotNull
    private String orderMan;

    @ManyToOne
    @JoinColumn(name ="orders_id")
    private Orders orders;

    @OneToMany(mappedBy = "id")
    private List<ChattingMessage> chattingMessageList;

    @Builder
    public ChattingRoom(String title, String deliveryMan, Orders orders) {
        this.id = id;
        this.deliveryMan = deliveryMan;
        this.orders = orders;
        this.orderMan = orders.getNickName();
    }

    public ChattingRoom() {

    }
}

