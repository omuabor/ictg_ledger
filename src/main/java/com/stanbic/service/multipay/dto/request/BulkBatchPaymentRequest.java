package com.stanbic.service.multipay.dto.request;



import java.util.ArrayList;

public class BulkBatchPaymentRequest {
    public double totalAmount;
    public int itemCount;
    public String debitAccountNumber;
    public String debitBankCode;
    public String debitNarration;

}
