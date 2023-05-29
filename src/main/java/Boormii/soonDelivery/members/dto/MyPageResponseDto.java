package Boormii.soonDelivery.members.dto;

import Boormii.soonDelivery.members.domain.Members;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MyPageResponseDto {

    @NotNull
    private String nickName;

    @Nullable
    private String department;

    @NotNull
    private String name;

    @Nullable
    private String defaultDeliveryAddress;

    public static MyPageResponseDto register(Members members) {
        MyPageResponseDto myPageResponseDto = new MyPageResponseDto();
        myPageResponseDto.setNickName(members.getNickName());

        if (members.getDepartment() != null) {
            myPageResponseDto.setDepartment(members.getDepartment());
        }

        if (members.getName() != null) {
            myPageResponseDto.setName(members.getName());
        }

        return myPageResponseDto;
    }
}
