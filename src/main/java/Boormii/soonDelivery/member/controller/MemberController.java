package Boormii.soonDelivery.member.controller;

import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.member.domain.Members;
import Boormii.soonDelivery.member.dto.JoinRequestDto;
import Boormii.soonDelivery.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping("api/join")
    public CommonResponse<Object> join(@RequestBody JoinRequestDto joinRequestDto)
    {
        Members members = Members.registerMember(joinRequestDto);
        return responseService.getSuccessResponse("회원 가입 성공", memberService.join(members));
    }

//    이메일로 인증번호 전송
    @GetMapping("/api/sendCertification/{email}")
    public void validateDuplicatedId(@PathVariable("email") String email)
    {
        memberService.validateDuplicateEmail(email);
    }

//    인증번호 체크


//    닉네임 중복 검증
    @GetMapping("/api/checkNickname/{nickname}")
    public String validateDuplicatedNickname(@PathVariable("nickname") String nickname)
    {
        return memberService.validateDuplicateNickName(nickname);

    }

}
