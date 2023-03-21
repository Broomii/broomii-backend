package Boormii.soonDelivery.chat.domain;

import Boormii.soonDelivery.chat.dto.CreateChattingRoomRequestDto;
import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ChattingRoom {

    @Id
    @GeneratedValue
    @Column(name = "chatting_room_id")
    private Long id;
    @NotNull
    private Long deliveryManId;

    @ManyToOne
    @JoinColumn(name ="orders_id")
    private Orders orders;

    @OneToMany(mappedBy = "chattingRoom")
    private List<ChattingMessage> chattingMessageList = new ArrayList<>();

    @Builder
    public ChattingRoom(Orders orders, Long deliveryManId) {
        this.deliveryManId = deliveryManId;
        this.orders = orders;
    }

    public ChattingRoom() {

    }
    public void addChattingMessage(ChattingMessage chattingMessage) {
        this.chattingMessageList.add(chattingMessage);
    }
}

