package Boormii.soonDelivery.members.dto;

import Boormii.soonDelivery.members.domain.Members;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfileResponseDto {

    @NotNull
    private String name;

    @NotNull
    private String nickName;

    @NotNull
    private String department;

    @NotNull
    private String defaultDeliveryAddress;

    public static ProfileResponseDto register(Members members){
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        profileResponseDto.setName(members.getName());
        profileResponseDto.setDepartment(members.getDepartment());
        profileResponseDto.setNickName(members.getNickName());
        profileResponseDto.setDefaultDeliveryAddress(members.getDefaultDeliveryAddress());

        return profileResponseDto;
    }
}
