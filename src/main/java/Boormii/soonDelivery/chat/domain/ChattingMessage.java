package Boormii.soonDelivery.chat.domain;

import Boormii.soonDelivery.chat.dto.ChatMessageDto;
import Boormii.soonDelivery.orders.domain.Orders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class ChattingMessage {

    @Id
    @GeneratedValue
    @Column(name = "chatting_message_id")
    private Long id;

    @NotNull
    private String sender;

    @NotNull
    private String message;

    @NotNull
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "chatting_room_id")
    @JsonIgnore
    @NotNull
    private ChattingRoom chattingRoom;

    @Builder
    public ChattingMessage(ChatMessageDto chatMessageDto, ChattingRoom chattingRoom) {
        this.sender = chatMessageDto.getSender();
        this.message = chatMessageDto.getMessage();
        this.createAt = LocalDateTime.now();
        this.chattingRoom = chattingRoom;

    }
    public ChattingMessage() {

    }
}
