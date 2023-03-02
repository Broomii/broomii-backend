package Boormii.soonDelivery.chat.controller;

import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.chat.dto.CreateChattingRoomRequestDto;
import Boormii.soonDelivery.chat.utils.ChatRoom;
import Boormii.soonDelivery.chat.service.ChatService;
import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final ResponseService responseService;
    private final JwtUtils jwtUtils;

    @PostMapping
    public CommonResponse<Object> createChattingRoom(@RequestBody CreateChattingRoomRequestDto createChattingRoomRequestDto) {
         return responseService.getSuccessResponse("채팅방 생성 성공", chatService.createRoom(createChattingRoomRequestDto));
    }
    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }

    @GetMapping("/getChattingList")
    public CommonResponse<Object> getChattingList(HttpServletRequest http) {
        return responseService.getSuccessResponse("성공", chatService.getChattingList(jwtUtils.getEmailFromRequestHeader(http)));
    }
}
