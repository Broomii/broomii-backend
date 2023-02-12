package Boormii.soonDelivery.members.domain;

import Boormii.soonDelivery.members.dto.JoinRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Members {
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
    private String department;
    private Sex sex;
    private String defaultDeliveryAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public static Members registerMember(JoinRequestDto joinRequestDto) {
        Members members = new Members();
        members.email = joinRequestDto.getEmail();
        members.defaultDeliveryAddress = joinRequestDto.getDefaultDeliveryAddress();
        members.name = joinRequestDto.getName();
        members.nickName = joinRequestDto.getNickName();
        members.password = joinRequestDto.getPassword();
        members.phoneNumber = joinRequestDto.getPhoneNumber();
        members.department = joinRequestDto.getDepartment();
        members.sex = Sex.valueOf(joinRequestDto.getSex());
        members.authority = Authority.ROLE_USER;
        return members;
    }

    public static Members createMemberByEmailAndPW(String email, String password) {
        Members members = new Members();
        members.setEmail(email);
        members.setPassword(password);

        return members;
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