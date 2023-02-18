package Boormii.soonDelivery.members.controller;

import Boormii.soonDelivery.global.jwt.JwtUtils;
import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.members.repository.MembersRepository;
import Boormii.soonDelivery.members.service.MyPageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final ResponseService responseService;
    private final MyPageService myPageService;
    private final JwtUtils jwtUtils;

    @GetMapping("mypage/get")
    public CommonResponse<Object> getMyPage(HttpServletRequest http){
        return responseService.getSuccessResponse("페이지 조회 성공", myPageService.getMyPage(jwtUtils.getEmailFromRequestHeader(http)));
    }
}
