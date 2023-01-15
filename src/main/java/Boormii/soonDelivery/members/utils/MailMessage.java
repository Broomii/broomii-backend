package Boormii.soonDelivery.members.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

public class MailMessage {
    private JavaMailSender javaMailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    public MailMessage(JavaMailSender mailSender) throws MessagingException {
        this.javaMailSender = mailSender;
        message = this.javaMailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    public void setText(String htmlContent) throws MessagingException {
        messageHelper.setSubject(htmlContent);
    }

    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
    }

    public void setTo(String email) throws MessagingException {
        messageHelper.setSubject(email);
    }

    public String getNewAccountConfirmMsgForm(String toEmail, String authKey) {
        String msg = "";
        msg += "<div style=\"margin: 0, auto; width: 50%; padding:36px 24px\">";
        msg += "<h1 style=\"font-size:36px;\"> 이메일 인증 코드</h1>";
        msg += "<br>";
        msg += "<div>";
        msg += "        <p>" + toEmail + "님, 안녕하세요.</p>\n" +
                "        <p>귀하의 이메일 주소를 통해 Broomii 계정 생성에 대한 요청이 전송되었습니다..</p>\n" +
                "        <p>Pansori 인증 코드는 다음과 같습니다.</p>\n" + "\n" +
                "        <p style=\"background-color: rgba(0, 0, 0, 0.1); font-size: 48px; padding:8px 24px;\">" +  authKey +  "</p>\n" + "\n" +
                "        <p>이 코드를 요청하지 않았다면 다른 사람이" + toEmail + "에 액세스하려고 시도하는 것일 수 있습니다.</p>\n" +
                "        <p>누구에게도 이 코드를 전달하거나 제공하면 안 됩니다.</p>\n" +
                "        <p>Broomii팀</p>";
        msg += "</div></div>";
        return msg;
    }
    public void send() {
        javaMailSender.send(message);
    }
}
