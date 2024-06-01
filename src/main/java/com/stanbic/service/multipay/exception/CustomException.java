package com.stanbic.service.multipay.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private String message;

    private String messageDetails;

}
