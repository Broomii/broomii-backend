package Boormii.soonDelivery.chat.domain;

import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class ChattingRoom {

    @Id
    @GeneratedValue
    @Column(name = "chatting_room_id")
    private Long id;
    @NotNull
    private String deliveryMan;

    private String latestMessage;

    @ManyToOne
    @JoinColumn(name ="orders_id")
    private Orders orders;

    @Builder
    public ChattingRoom(String title, String deliveryMan, Orders orders) {
        this.id = id;
        this.deliveryMan = deliveryMan;
        this.orders = orders;
    }
}

