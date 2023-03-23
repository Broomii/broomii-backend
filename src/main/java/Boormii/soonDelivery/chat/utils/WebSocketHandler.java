package Boormii.soonDelivery.chat.utils;

import Boormii.soonDelivery.chat.utils.ChatRoom;
import Boormii.soonDelivery.chat.dto.ChatMessageDto;
import Boormii.soonDelivery.chat.service.ChatService;
import Boormii.soonDelivery.global.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessageDto chatMessageDto = objectMapper.readValue(payload, ChatMessageDto.class);
        ChatRoom chatRoom = chatService.findRoomById(chatMessageDto.getRoomId());
        chatRoom.handlerActions(session, chatMessageDto, chatService);
    }
}
