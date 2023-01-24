package Boormii.soonDelivery.members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;
}
