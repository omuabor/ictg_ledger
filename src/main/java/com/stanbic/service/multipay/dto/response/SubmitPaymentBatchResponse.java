package com.stanbic.service.multipay.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubmitPaymentBatchResponse {
    public String timestamp;
    public String message;
    public String status;
    public String batchId;
    public int itemCount;
    public double totalAmount;
}
