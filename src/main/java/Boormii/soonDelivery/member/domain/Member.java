package Boormii.soonDelivery.member.domain;

import Boormii.soonDelivery.member.dto.JoinRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
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
    private String defaultDeliveryAddress;

    public static Member registerMember(JoinRequestDto joinRequestDto){
        Member member = new Member();
        member.email = joinRequestDto.getEmail();
        member.defaultDeliveryAddress = joinRequestDto.getDefaultDeliveryAddress();
        member.name = joinRequestDto.getName();
        member.nickName = joinRequestDto.getNickName();
        member.password = joinRequestDto.getPassword();
        member.phoneNumber = joinRequestDto.getPhoneNumber();
        return member;
    }
}




//- 이름 *
//- 닉네임 (중복 확인) *
//- 이메일 (중복 확인, 학교 이메일 확인) *
//- 비밀번호(8글자이상, 영문자, 숫자, 특수문자 포함) *
//- 비밀번호 확인 *
//- 기본 배달 주소
//- 전화번호 * // 받긴받자(필수) →  010받고 프론트에서 검사 → 박스 넘어가게?
//- 학과(드랍다운)타이핑 //빼고 (선택)
//- 성별(체크박스) // 뺄 (선택)
//약관 만들어야되나?