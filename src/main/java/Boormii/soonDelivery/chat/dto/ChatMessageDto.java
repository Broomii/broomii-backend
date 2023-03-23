package Boormii.soonDelivery.chat.dto;

import lombok.Data;

@Data
public class ChatMessageDto {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private Long roomId;
    private String message;
    private String token;
}
