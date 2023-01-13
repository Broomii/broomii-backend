package Boormii.soonDelivery.global.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseService {

    public <T> CommonResponse<Object> getSuccessResponse(String msg, T data){
        return CommonResponse.builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .message(msg)
                .data(data)
                .build();
    }
}
