package com.stanbic.service.multipay.dto.request;

import lombok.Data;

@Data
public class CreateLedgerRequest {

    private String ledgerName;

    private String description;

    private String createdBy;
}
