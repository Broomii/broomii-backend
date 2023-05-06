package Boormii.soonDelivery.chat.dto;

import lombok.Data;

@Data
public class ChatMessageResponseDto {

    private ChatMessageDto.MessageType type;
    private Long roomId;
    private String message;
    private String nickName;

    public ChatMessageResponseDto(ChatMessageDto chatMessageDto, String nickName) {
        this.type = chatMessageDto.getType();
        this.roomId = chatMessageDto.getRoomId();
        this.message = chatMessageDto.getMessage();
        this.nickName = nickName;
    }
}
