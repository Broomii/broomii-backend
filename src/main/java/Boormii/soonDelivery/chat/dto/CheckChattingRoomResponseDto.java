package Boormii.soonDelivery.chat.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class CheckChattingRoomResponseDto {

    @Nullable
    private Long id;

    private Boolean isExistRoom;

    public CheckChattingRoomResponseDto(Boolean isExistRoom){
        this.isExistRoom = isExistRoom;
    }
}
