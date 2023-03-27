package Boormii.soonDelivery.chat.controller;

import Boormii.soonDelivery.chat.dto.CreateChattingRoomRequestDto;
import Boormii.soonDelivery.chat.service.ChatService;
import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final ResponseService responseService;
    private final JwtUtils jwtUtils;

    // 채팅방 생성
    @PostMapping
    public CommonResponse<Object> createChattingRoom(@RequestBody CreateChattingRoomRequestDto createChattingRoomRequestDto, HttpServletRequest http) {
        return responseService.getSuccessResponse("채팅방 생성 성공", chatService.createRoom(createChattingRoomRequestDto, jwtUtils.getEmailFromRequestHeader(http)));
    }

    // 채팅 목록 반환
    @GetMapping("/getChattingList")
    public CommonResponse<Object> getChattingList(HttpServletRequest http) {
        return responseService.getSuccessResponse("채팅방 목록 반환 성공", chatService.getChattingList(jwtUtils.getEmailFromRequestHeader(http)));
    }

    // 채팅방의 채팅 리스트 반환
    @GetMapping("/getChattings/{id}")
    public CommonResponse<Object> getChattings(@PathVariable("id") Long id) {
        return responseService.getSuccessResponse("채팅 반환 성공", chatService.getChattings(id));
    }

    // 채팅방 존재 여부 반환
    @GetMapping("/checkChattingRoom/{id}")
    public CommonResponse<Object> checkChattingRoom(@PathVariable("id") Long id, HttpServletRequest http) {
        return responseService.getSuccessResponse("채팅방 여부 반환 성공", chatService.checkChattingRoom(id, jwtUtils.getEmailFromRequestHeader(http)));
    }
}
