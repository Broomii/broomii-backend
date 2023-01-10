package Boormii.soonDelivery.member.controller;

import Boormii.soonDelivery.member.domain.Member;
import Boormii.soonDelivery.member.dto.JoinRequestDto;
import Boormii.soonDelivery.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
