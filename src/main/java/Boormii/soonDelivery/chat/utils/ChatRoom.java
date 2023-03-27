package Boormii.soonDelivery.chat.utils;

import Boormii.soonDelivery.chat.dto.ChatMessageDto;
import Boormii.soonDelivery.chat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private Long id;
    private String name;
    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void handlerActions(WebSocketSession session, ChatMessageDto chatMessageDto, ChatService chatService) {
        if (chatMessageDto.getType().equals(ChatMessageDto.MessageType.ENTER)) {
            sessions.add(session);
        }
        chatService.saveMessage(chatMessageDto);
        sendMessage(chatMessageDto, chatService);
    }

    public static void deleteSession(WebSocketSession webSocketSession){
        sessions.remove(webSocketSession);
    }
    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}