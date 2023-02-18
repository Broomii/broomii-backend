package Boormii.soonDelivery.members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditProfileRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String nickName;

    @NotNull
    private String department;

    @NotNull
    private String defaultDeliveryAddress;
}
