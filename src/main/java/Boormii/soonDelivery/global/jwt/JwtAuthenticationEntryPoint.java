package Boormii.soonDelivery.global.jwt;

import Boormii.soonDelivery.global.exception.ErrorCode;
import Boormii.soonDelivery.global.response.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ResponseService responseService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ErrorCode errorCode = (ErrorCode) request.getAttribute("exception");
        setResponse(response, errorCode);
    }
    private void setResponse(HttpServletResponse response, ErrorCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        JSONObject responseJson = new JSONObject();

        if(exceptionCode.equals(ErrorCode.EXPIRED_JWT)){
            response.setStatus(exceptionCode.getHttpStatus().value());
        }
        else{ response.setStatus(430); }

        responseJson.put("code", exceptionCode.getHttpStatus().name());
        responseJson.put("message", exceptionCode.getMessage());


        response.getWriter().print(responseJson);
    }
}
