package Boormii.soonDelivery.members.dto.token;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshRequestDto {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;
}
