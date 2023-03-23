package Boormii.soonDelivery.chat.service;

import Boormii.soonDelivery.chat.domain.ChattingMessage;
import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.chat.dto.*;
import Boormii.soonDelivery.chat.repository.ChattingMessageRepository;
import Boormii.soonDelivery.chat.repository.ChattingRoomRepository;
import Boormii.soonDelivery.chat.utils.ChatRoom;
import Boormii.soonDelivery.global.exception.ApiException;
import Boormii.soonDelivery.global.jwt.JwtUtils;
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
    private final JwtUtils jwtUtils;
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
        System.out.println(chatRooms.values());
        return chatRooms.get(roomId);
    }

//    public ChatRoom findRoomById(Long id) {
//        return chatRoomRepository.findById(id).get();
//    }

    @Transactional
    public ChatRoom createRoom(CreateChattingRoomRequestDto createChattingRoomRequestDto, String email) {
        Orders orders = ordersRepository.findById(createChattingRoomRequestDto.getOrderId()).get();
        Members deliveryMan = membersRepository.findByEmail(email).get();
        ChattingRoom chattingRoom = new ChattingRoom(orders, deliveryMan);

        deliveryMan.addChattingRoom(chattingRoom);
        orders.addChattingRoom(chattingRoom);
        chattingRoomRepository.save(chattingRoom);

        ChatRoom chatRoom = ChatRoom.builder()
                .id(chattingRoom.getId())
                .name(deliveryMan.getNickName())
                .build();
        chatRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {
        String email = jwtUtils.getEmailFromToken(chatMessageDto.getToken());
        ChattingRoom chattingRoom = chattingRoomRepository.findById(chatMessageDto.getRoomId());
        Members members = membersRepository.findByEmail(email).get();
        ChattingMessage chattingMessage = new ChattingMessage(chatMessageDto, chattingRoom, members);
        chattingMessageRepository.save(chattingMessage);

        members.addChattingMessage(chattingMessage);
        chattingRoom.addChattingMessage(chattingMessage);
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

        Members member = membersRepository.findByEmail(email).get();

        // email order.getnickname,
        ChattingRoomDto chattingRoomDto;
        List<ChattingRoomDto> chattingRoomDtoList = new ArrayList<>();
        ChattingListDto chattingListDto = new ChattingListDto();
        // member 내에 있는 order, chattingroomlist 통해서 반환

        // member가 배달자인 order chattingRoomDtoList에 넣기
        for (ChattingRoom chattingRoom : member.getChattingRoomList()) {
            chattingRoomDto = ChattingRoomDto.createDtoAsDeliveryMan(chattingRoom);
            chattingRoomDtoList.add(chattingRoomDto);
        }

        // member가 주문자인 order chattingRoomDtoList에 넣기
        for (Orders order : member.getOrderList()) {
            for (ChattingRoom chattingRoom : order.getChattingRoomList()){
                chattingRoomDto = ChattingRoomDto.createDtoAsOrderMan(chattingRoom);
                chattingRoomDtoList.add(chattingRoomDto);
            }
        }

        chattingListDto.setChattingRoomDtoList(chattingRoomDtoList);
        return chattingListDto;
    }

    public CheckChattingRoomResponseDto checkChattingRoom(Long orderId, String email){
        Orders orders = ordersRepository.findById(orderId).get();
        String checkNickName = membersRepository.findByEmail(email).get().getNickName();
        CheckChattingRoomResponseDto checkChattingRoomResponseDto = new CheckChattingRoomResponseDto(false);

        // 해당 주문과 연결된 채팅방 조회
        for(ChattingRoom chattingRoom : orders.getChattingRoomList()){
           if(chattingRoom.getDeliveryMan() != null && chattingRoom.getDeliveryMan().getNickName().equals(checkNickName)) {
                checkChattingRoomResponseDto.setIsExistRoom(true);
                checkChattingRoomResponseDto.setId(chattingRoom.getId());
            }
        }
        return checkChattingRoomResponseDto;
    }
}
