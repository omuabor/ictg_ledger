package com.stanbic.service.multipay.enums;

import lombok.Getter;

@Getter
public enum NibssStatus {

    _00("00", "Successful"),
    _002("002", "Batch open, Receiving Items"),

    _003("003", "Batch closed. Ready for Payment"),

    _400("400", "Payment Batch Already Exists"),

    _404("404", "Payment Batch does not exist");

    private String status;

    private String description;


    NibssStatus(String status, String description){
        this.status = status;
        this.description = description;
    }


}
