package Boormii.soonDelivery.chat.service;

import Boormii.soonDelivery.chat.domain.ChattingMessage;
import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.chat.dto.*;
import Boormii.soonDelivery.chat.repository.ChattingMessageRepository;
import Boormii.soonDelivery.chat.repository.ChattingRoomRepository;
import Boormii.soonDelivery.chat.utils.ChatRoom;
import Boormii.soonDelivery.global.exception.ApiException;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.orders.domain.Orders;
import Boormii.soonDelivery.orders.repository.OrdersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingMessageRepository chattingMessageRepository;
    private final MembersRepository membersRepository;
    private final ObjectMapper objectMapper;
    private final OrdersRepository ordersRepository;
    private Map<Long, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    //    public List<ChatRoom> findAllRoom() {
//        return chatRoomRepository.findAll();
//    }
    public ChatRoom findRoomById(Long roomId) {
        return chatRooms.get(roomId);
    }

//    public ChatRoom findRoomById(Long id) {
//        return chatRoomRepository.findById(id).get();
//    }

    @Transactional
    public ChatRoom createRoom(CreateChattingRoomRequestDto createChattingRoomRequestDto) {
        Orders orders = ordersRepository.findById(createChattingRoomRequestDto.getOrderId()).get();
        ChattingRoom chattingRoom = new ChattingRoom(createChattingRoomRequestDto.getTitle(), createChattingRoomRequestDto.getDeliveryMan(), orders);
        chattingRoomRepository.save(chattingRoom);

        ChatRoom chatRoom = ChatRoom.builder()
                .id(chattingRoom.getId())
                .name(createChattingRoomRequestDto.getDeliveryMan())
                .build();
        chatRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(chatMessageDto.getRoomId());
        ChattingMessage chattingMessage = new ChattingMessage(chatMessageDto, chattingRoom);
        chattingRoom.addChattingMessage(chattingMessage);
        chattingMessageRepository.save(chattingMessage);
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            throw new ApiException(HttpStatus.BAD_GATEWAY, "입출력 오류");
        }
    }

    public List<ChattingMessageDto> getChattings(Long roomId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(roomId);
        chattingRoom.getChattingMessageList();

        List<ChattingMessageDto> chattingMessageDtoList = new ArrayList<>();

        for (ChattingMessage chattingMessage : chattingRoom.getChattingMessageList()) {
            ChattingMessageDto dto = ChattingMessageDto.createDto(chattingMessage);
            chattingMessageDtoList.add(dto);
        }
        return chattingMessageDtoList;
    }

    public ChattingListDto getChattingList(String email) {
        ChattingRoomDto chattingRoomDto;
        List<ChattingRoomDto> chattingRoomDtoList = new ArrayList<>();
        ChattingListDto chattingListDto = new ChattingListDto();
        String nickName = membersRepository.findByEmail(email).get().getNickName();
        List<Object[]> result = chattingRoomRepository.getChattingList(nickName);
        for (Object[] row : result ) {
            chattingRoomDto = new ChattingRoomDto();
            chattingRoomDto.setChattingRoomId((Long) row[0]);
            ChattingRoom chattingRoom = chattingRoomRepository.findById((Long) row[0]);
            if ( row[1].equals(nickName)) {
                chattingRoomDto.setReceiver(String.valueOf(row[2]));
            } else {
                chattingRoomDto.setReceiver(String.valueOf(row[1]));
            }
            chattingRoomDtoList.add(chattingRoomDto);
        }
        chattingListDto.setChattingRoomDtoList(chattingRoomDtoList);
        return chattingListDto;
    }

}
