package com.stanbic.service.multipay.dto.response;

import lombok.Data;

@Data
public class LedgerSummaryResponse {

    private String ledgerType;

    private Double inflow;

    private Double outflow;

    private Double ledgerBalance;

    private Double grandTotal;
}
