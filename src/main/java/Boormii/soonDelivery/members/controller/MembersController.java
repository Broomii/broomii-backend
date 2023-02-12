package Boormii.soonDelivery.members.controller;

import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.members.domain.Members;
import Boormii.soonDelivery.members.dto.ConfirmCertificationRequestDto;
import Boormii.soonDelivery.members.dto.JoinRequestDto;
import Boormii.soonDelivery.members.dto.LoginRequestDto;
import Boormii.soonDelivery.members.dto.token.RefreshRequestDto;
import Boormii.soonDelivery.members.dto.token.TokenDto;
import Boormii.soonDelivery.members.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MembersController {
    private final MembersService membersService;
    private final ResponseService responseService;

    @PostMapping("members/join")
    public CommonResponse<Object> join(@RequestBody JoinRequestDto joinRequestDto) {
        Members members = Members.registerMember(joinRequestDto);
        return responseService.getSuccessResponse("회원 가입 성공", membersService.join(members));
    }

    @PostMapping("members/login")
    public CommonResponse<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenDto tokenDto = membersService.login(loginRequestDto);
        return responseService.getSuccessResponse("로그인 성공", tokenDto);
    }

    //    안씀
    @GetMapping("members/sendCertification/{email}")
    public CommonResponse<Object> validateDuplicatedId(@PathVariable("email") String email) {
        membersService.validateDuplicateEmail(email);
        return responseService.getSuccessResponse("인증 번호 전송 성공", null);
    }

    //    인증번호 확인
    @PostMapping("members/confirmCertification")
    public CommonResponse<Object> confirmCertification(@RequestBody ConfirmCertificationRequestDto confirmCertificationRequestDto) {
        membersService.confirmCertification(confirmCertificationRequestDto);
        return responseService.getSuccessResponse("인증 번호 확인 완료", null);
    }

    //    닉네임 중복 검증
    @GetMapping("members/checkNickname/{nickname}")
    public CommonResponse<Object> validateDuplicatedNickname(@PathVariable("nickname") String nickname) {
        return responseService.getSuccessResponse("인증 번호 전송 성공", membersService.validateDuplicateNickName(nickname));
    }

    //    사용자 기본 주소 반환
    @GetMapping("members/getDefaultAddress/{email}")
    public String getDefaultAddress(@PathVariable("email") String email) {
        return membersService.getDefaultAddress(email);
    }

    @PostMapping("members/refreshToken")
    public CommonResponse<Object> refresh(@RequestBody RefreshRequestDto refreshRequestDto){
        return responseService.getSuccessResponse("토큰 재발급 성공", membersService.reissueToken(refreshRequestDto));
    }

}
