package Boormii.soonDelivery.chat.utils;

import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.chat.dto.ChatMessageDto;
import Boormii.soonDelivery.chat.dto.ChatMessageResponseDto;
import Boormii.soonDelivery.chat.repository.ChattingRoomRepository;
import Boormii.soonDelivery.chat.service.ChatService;
import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.repository.MembersRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
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
    private <T> void sendMessage(ChatMessageDto message, ChatService chatService) {
        sessions.parallelStream()
                .forEach(session -> chatService.sendMessage(session, message));
    }
}