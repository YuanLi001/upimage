package com.example.upimage.exception;

import com.example.upimage.utils.AppException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YuanLi
 * @version 1.0
 * @date 2022/3/26 19:32
 */
@RestControllerAdvice
public class GlobalException {

//    @ExceptionHandler(value = AuthenticationServiceException.class)
//    public String toHandleAuthenticationServiceException(Exception e) {
//        e.printStackTrace();
//        return e.getMessage();
//    }

    @ExceptionHandler(value = AppException.class)
    public String toHandleUnexpectException(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }

}
