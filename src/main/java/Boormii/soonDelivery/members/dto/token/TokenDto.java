package Boormii.soonDelivery.members.dto.token;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TokenDto {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

    @NotNull
    private Long refreshTokenExpirationTime;
}
