package Boormii.soonDelivery.member.controller;

import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
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
    private final ResponseService responseService;

    @PostMapping("api/join")
    public CommonResponse<Object> join(@RequestBody JoinRequestDto joinRequestDto)
    {
        Member member = Member.registerMember(joinRequestDto);
        return responseService.getSuccessResponse("회원 가입 성공", memberService.join(member));
    }

}
