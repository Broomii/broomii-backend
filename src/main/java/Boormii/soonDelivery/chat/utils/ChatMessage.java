package Boormii.soonDelivery.chat.utils;

import lombok.Data;


@Data
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private Long id;
    private String sender;
    private String message;
}
