package Boormii.soonDelivery.members.controller;

import Boormii.soonDelivery.global.response.CommonResponse;
import Boormii.soonDelivery.global.response.ResponseService;
import Boormii.soonDelivery.members.dto.MailRequestDto;
import Boormii.soonDelivery.members.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailsController {
    private final MailService mailService;
    private final ResponseService responseService;

//    @PostMapping("mail/sendCertificationNumber")
//    public CommonResponse<Object> sendCertificationNumber(@RequestBody MailRequestDto mailRequestDto){
//        mailService.mailSend(mailRequestDto.getEmail());
//        return responseService.getSuccessResponse("메일 전송 완료", null);
//    }
}
