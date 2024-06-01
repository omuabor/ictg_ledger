package com.stanbic.service.multipay.exception;

import com.stanbic.service.multipay.dto.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.stanbic.service.multipay.enums.ResponseEnum._99;

@RestControllerAdvice
public class ExceptionHandlerClass {

//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<GeneralResponse> handleCustomException(CustomException ex) {
//
//        return ResponseEntity.status(_99.getHttpStatus()).body(new GeneralResponse(_99.getResponseCode(),
//                ex.getMessage(), ex.getMessageDetails()));
//    }


}
