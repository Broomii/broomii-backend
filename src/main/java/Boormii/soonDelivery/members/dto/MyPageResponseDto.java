package Boormii.soonDelivery.members.dto;

import Boormii.soonDelivery.members.domain.Members;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MyPageResponseDto {

    @NotNull
    private String nickName;

    @NotNull
    private String department;

    public static MyPageResponseDto register(Members members){
        MyPageResponseDto myPageResponseDto = new MyPageResponseDto();
        myPageResponseDto.setNickName(members.getNickName());
        myPageResponseDto.setDepartment(members.getDepartment());

        return myPageResponseDto;
    }
}
