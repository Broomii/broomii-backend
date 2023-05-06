package Boormii.soonDelivery.members.controller;

import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.members.dto.EditProfileRequestDto;
import Boormii.soonDelivery.members.dto.JoinRequestDto;
import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.members.service.MyPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mypage")
public class MyPageController {

    private final ResponseService responseService;
    private final MyPageService myPageService;
    private final JwtUtils jwtUtils;

    @GetMapping("/get")
    public CommonResponse<Object> getMyPage(HttpServletRequest http){
        return responseService.getSuccessResponse("페이지 조회 성공", myPageService.getMyPage(jwtUtils.getEmailFromRequestHeader(http)));
    }

    @PutMapping("/editProfile")
    public CommonResponse<Object> editProfile(@RequestBody EditProfileRequestDto editProfileRequestDto, HttpServletRequest http){
        return responseService.getSuccessResponse("프로필 수정 성공", myPageService.editProfile(editProfileRequestDto, jwtUtils.getEmailFromRequestHeader(http)));
    }

    // 프로필 조회
    @GetMapping("/getProfile")
    public CommonResponse<Object> getProfile(HttpServletRequest http){
        return responseService.getSuccessResponse("프로필 조회 성공", myPageService.getProfile(jwtUtils.getEmailFromRequestHeader(http)));
    }
}
