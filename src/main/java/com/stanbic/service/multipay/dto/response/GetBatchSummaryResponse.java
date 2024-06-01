package com.stanbic.service.multipay.dto.response;

import lombok.Data;

@Data
public class GetBatchSummaryResponse {
    public String batchId;
    public String failed;
    public int itemCount;
    public String message;
    public String pending;
    public String processing;
    public String status;
    public String successful;
    public String timestamp;
    public int totalAmount;
}
