package com.stanbic.service.multipay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GeneralResponse {

    private String responseCode;
    private String responseMsg;
    private  Object responseDetails;

    public GeneralResponse(String responseCode, String responseMsg, String key, String value) {
        HashMap<String, Object> responseInformation = new HashMap<>();
        responseInformation.put(key, value);
        this.responseDetails = responseInformation;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }


    public GeneralResponse(String responseCode, String responseMsg, Object responseDetails) {

        this.responseDetails = responseDetails;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

}
