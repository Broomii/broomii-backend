package Boormii.soonDelivery.chat.controller;

import Boormii.soonDelivery.chat.dto.CreateChattingRoomRequestDto;
import Boormii.soonDelivery.chat.utils.ChatRoom;
import Boormii.soonDelivery.chat.service.ChatService;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final ResponseService responseService;

    @PostMapping
    public CommonResponse<Object> createChattingRoom(@RequestBody CreateChattingRoomRequestDto createChattingRoomRequestDto) {
         return responseService.getSuccessResponse("채팅방 생성 성공", chatService.createRoom(createChattingRoomRequestDto));
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
