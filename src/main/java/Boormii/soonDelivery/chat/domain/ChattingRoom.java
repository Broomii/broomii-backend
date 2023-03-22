package Boormii.soonDelivery.chat.domain;

import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.orders.domain.Orders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    @Nullable
    private Members deliveryMan;

    @ManyToOne
    @JoinColumn(name ="orders_id")
    @JsonIgnore
    @Nullable
    private Orders orders;

    @OneToMany(mappedBy = "chattingRoom")
    private List<ChattingMessage> chattingMessageList = new ArrayList<>();

    @Builder
    public ChattingRoom(Orders orders, Members deliveryMan) {
        this.deliveryMan = deliveryMan;
        this.orders = orders;
    }

    public ChattingRoom() {

    }
    public void addChattingMessage(ChattingMessage chattingMessage) {
        this.chattingMessageList.add(chattingMessage);
    }
    public void disconnectMembers() {
        this.deliveryMan = null;
    }

    public void disconnectOrders() {
        this.orders = null;
    }
}

