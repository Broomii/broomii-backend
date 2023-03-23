package Boormii.soonDelivery.chat.dto;

import Boormii.soonDelivery.chat.domain.ChattingMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChattingMessageDto {

    @NotNull
    private Long id;

    @NotNull
    String sender;

    @NotNull
    String message;

    @NotNull
    LocalDateTime createAt;

    public static ChattingMessageDto createDto(ChattingMessage chattingMessage){
        ChattingMessageDto dto = new ChattingMessageDto();
        dto.id = chattingMessage.getId();
        dto.createAt = chattingMessage.getCreateAt();
        dto.message = chattingMessage.getMessage();
        dto.sender = chattingMessage.getSender().getName();

        return dto;
    }
}
