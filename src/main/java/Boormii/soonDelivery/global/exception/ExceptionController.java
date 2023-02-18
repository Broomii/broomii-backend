package Boormii.soonDelivery.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleBasicException(HttpServletRequest request, ApiException ex){
        return ErrorResponseEntity.toResponseEntity(ex);
    }
}
