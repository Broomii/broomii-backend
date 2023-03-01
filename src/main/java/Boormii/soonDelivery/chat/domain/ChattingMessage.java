package Boormii.soonDelivery.chat.domain;

import Boormii.soonDelivery.chat.dto.ChatMessageDto;
import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
public class ChattingMessage {

    @Id
    @GeneratedValue
    @Column(name = "chatting_message_id")
    Long id;

    @NotNull
    String sender;

    @NotNull
    String message;

    @NotNull
    LocalDateTime createAt;

    @Builder
    public ChattingMessage(ChatMessageDto chatMessageDto) {
        this.sender = chatMessageDto.getSender();
        this.message = chatMessageDto.getMessage();
        this.createAt = LocalDateTime.now();

    }

}
