package com.stanbic.service.multipay.dto.request;

import com.stanbic.service.multipay.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class LedgerDetails {

//    TODO: ensure transaction date is in best format
    private String transactionDate;
//    TODO: do validation for transaction type
    private TransactionType transactionType;
    private double amount;
    private String particulars;

}
