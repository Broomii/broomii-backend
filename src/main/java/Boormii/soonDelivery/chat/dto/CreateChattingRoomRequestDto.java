package Boormii.soonDelivery.chat.dto;

import lombok.Data;

@Data
public class CreateChattingRoomRequestDto {

    private String deliveryMan;

    private String title;

    private Long orderId;
}
