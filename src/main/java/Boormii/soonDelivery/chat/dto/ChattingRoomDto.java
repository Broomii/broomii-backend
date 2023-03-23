package Boormii.soonDelivery.chat.dto;

import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.orders.domain.Orders;
import lombok.Data;

@Data
public class ChattingRoomDto {
    public String receiver;
    public Long chattingRoomId;

    public static ChattingRoomDto createDtoAsDeliveryMan(ChattingRoom chattingRoom) {
        ChattingRoomDto chattingRoomDto = new ChattingRoomDto();

        // 조회 유저가 배달자인 경우에 주문자의 탈퇴 여부 체크
        if (chattingRoom.getOrders().getMembers() != null){
            chattingRoomDto.receiver = chattingRoom.getOrders().getMembers().getNickName();
        } else {
            chattingRoomDto.receiver = "알 수 없음";
        }
        chattingRoomDto.chattingRoomId = chattingRoom.getId();

        return chattingRoomDto;
    }

    public static ChattingRoomDto createDtoAsOrderMan(ChattingRoom chattingRoom) {
        ChattingRoomDto chattingRoomDto = new ChattingRoomDto();

        // 조회 유저가 주문자인 경우에 배달자의 탈퇴 여부 체크
        if (chattingRoom.getDeliveryMan() != null){
            chattingRoomDto.receiver = chattingRoom.getDeliveryMan().getNickName();
        } else {
            chattingRoomDto.receiver = "알 수 없음";
        }
        chattingRoomDto.chattingRoomId = chattingRoom.getId();

        return chattingRoomDto;
    }

}
