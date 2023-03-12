package Boormii.soonDelivery.members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteRequestDto {
    @NotNull
    String email;
}
