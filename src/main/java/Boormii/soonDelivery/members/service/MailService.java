package Boormii.soonDelivery.members.service;

import Boormii.soonDelivery.members.dto.ReportMailRequestDto;
import Boormii.soonDelivery.members.utils.MailMessage;
//import Boormii.soonDelivery.members.utils.RedisUtil;
import Boormii.soonDelivery.members.utils.RedisUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final static Integer FIVE_MINUTE = 300000;

    private final JavaMailSender javaMailSender;

    private static final String from_address = "olzlgur@naver.com";
    private static final String text = "이메일 인증 코드";

    private final JavaMailSender emailSender;
    private final RedisUtil redisUtil;


    // 인증번호 전송 메서드
    public String mailSend(String email) {
        String certificationKey = createKey();
        try {
            MailMessage mailMessage = new MailMessage(javaMailSender);
            mailMessage.setSubject(text);
            mailMessage.setFrom(MailService.from_address, "Broomii");
            mailMessage.setTo(email);

            redisUtil.setDataExpire(email, certificationKey, FIVE_MINUTE);

            String message = mailMessage.emailDuplicateCheckMsgForm(email, certificationKey);

            mailMessage.setText(message);
            mailMessage.send();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "메일 전송 완료";
    }

    public String reportMailSend(ReportMailRequestDto reportMailRequestDto) {
        String certificationKey = createKey();
        try {
            MailMessage mailMessage = new MailMessage(javaMailSender);
            mailMessage.setSubject("부르미 신고 메일");
            mailMessage.setFrom(MailService.from_address, "Broomii");
            mailMessage.setTo(from_address);

            String message = "Target: " + reportMailRequestDto.getTarget() + "\n OrderId: " + String.valueOf(reportMailRequestDto.getOrderId());

            mailMessage.setText(message);
            mailMessage.send();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "메일 전송 완료";
    }

    private String createKey(){
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 100000);
    }

}
