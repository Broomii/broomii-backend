package Boormii.soonDelivery.member.controller;

import Boormii.soonDelivery.member.domain.Member;
import Boormii.soonDelivery.member.dto.JoinRequestDto;
import Boormii.soonDelivery.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("api/join")
    public Long join(@RequestBody JoinRequestDto joinRequestDto)
    {
        Member member = Member.registerMember(joinRequestDto);
        return memberService.join(member);
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
