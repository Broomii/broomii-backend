package Boormii.soonDelivery.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FilterException extends RuntimeException{
    ErrorCode errorCode;
}
