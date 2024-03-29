package Boormii.soonDelivery.members.controller;

import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.members.dto.*;
import Boormii.soonDelivery.members.dto.token.RefreshRequestDto;
import Boormii.soonDelivery.members.dto.token.TokenDto;
import Boormii.soonDelivery.members.service.MembersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MembersController {
    private final MembersService membersService;
    private final ResponseService responseService;
    private final JwtUtils jwtUtils;

    @PostMapping("/join")
    public CommonResponse<Object> join(@RequestBody JoinRequestDto joinRequestDto) {
        return responseService.getSuccessResponse("회원 가입 성공", membersService.join(joinRequestDto));
    }

    @PostMapping("/login")
    public CommonResponse<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenDto tokenDto = membersService.login(loginRequestDto);
        return responseService.getSuccessResponse("로그인 성공", tokenDto);
    }

    @DeleteMapping("/delete")
    public CommonResponse<Object> delete(HttpServletRequest http) {
        membersService.delete(jwtUtils.getEmailFromRequestHeader(http));
        return responseService.getSuccessResponse("회원 탈퇴 성공" , null);
    }

    //    인증번호 확인
    @PostMapping("/confirmCertification")
    public CommonResponse<Object> confirmCertification(@RequestBody ConfirmCertificationRequestDto confirmCertificationRequestDto) {
        membersService.confirmCertification(confirmCertificationRequestDto);
        return responseService.getSuccessResponse("인증 번호 확인 완료", null);
    }

    //    닉네임 중복 검증
    @GetMapping("/checkNickname/{nickname}")
    public CommonResponse<Object> validateDuplicatedNickname(@PathVariable("nickname") String nickname) {
        return responseService.getSuccessResponse("사용 가능한 닉네임입니다.", membersService.validateDuplicateNickName(nickname));
    }

    //    사용자 기본 주소 반환
    @GetMapping("/getDefaultAddress")
    public CommonResponse<Object> getDefaultAddress(HttpServletRequest http) {
        return responseService.getSuccessResponse("기본 배송지 불러오기 성공", membersService.getDefaultAddress(jwtUtils.getEmailFromRequestHeader(http)));
    }

    @PostMapping("/refreshToken")
    public CommonResponse<Object> refresh(@RequestBody RefreshRequestDto refreshRequestDto){
        return responseService.getSuccessResponse("토큰 재발급 성공", membersService.reissueToken(refreshRequestDto));
    }

    @PostMapping("/editPassword")
    public CommonResponse<Object> editPassword(@RequestBody EditPasswordRequestDto editPasswordRequestDto) {
        return responseService.getSuccessResponse("비밀번호 변경 성공", membersService.editPassword(editPasswordRequestDto));
    }

}
