package com.stanbic.service.multipay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResponseEnum {

    _00("00", "Successful", HttpStatus.OK),

    _55("55", "Service Currently not available. Please Try Again later", HttpStatus.INTERNAL_SERVER_ERROR),

    _77("77", "Duplicate Request", HttpStatus.CONFLICT),

    _41("41", "Unauthorized", HttpStatus.UNAUTHORIZED),

    _99("99", "Operation Not Successful", HttpStatus.BAD_REQUEST);

    private String responseCode;

    private String responseMsg;

    private HttpStatus httpStatus;

}
