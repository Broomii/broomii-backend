package Boormii.soonDelivery.chat.dto;

import Boormii.soonDelivery.chat.domain.ChattingRoom;
import Boormii.soonDelivery.orders.domain.Orders;
import lombok.Data;

@Data
public class ChattingRoomDto {
    public String receiver;
    public Long chattingRoomId;

    public ChattingRoomDto createDtoByChattingRoom(ChattingRoom chattingRoom) {
        ChattingRoomDto chattingRoomDto = new ChattingRoomDto();
        chattingRoomDto.receiver = chattingRoom.getOrders().getMembers().getNickName();
        chattingRoomDto.chattingRoomId = chattingRoom.getId();

        return chattingRoomDto;
    }

    public ChattingRoomDto createDtoByOrder(Orders order) {
        ChattingRoomDto chattingRoomDto = new ChattingRoomDto();
        chattingRoomDto.receiver = order.getMembers().getNickName();
        chattingRoomDto.chattingRoomId = order.;

        return chattingRoomDto;
    }
}
