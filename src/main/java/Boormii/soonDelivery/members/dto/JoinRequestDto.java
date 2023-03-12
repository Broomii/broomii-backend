package Boormii.soonDelivery.members.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class JoinRequestDto {

    @NotNull
    private String name;

    @NotNull
    private String nickName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    private String department;

    private String sex;

    private String defaultDeliveryAddress;
}
