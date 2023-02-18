package Boormii.soonDelivery.members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EditPasswordRequestDto {
    @NotNull
    String email;

    @NotNull
    String password;
}
