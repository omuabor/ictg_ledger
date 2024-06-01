package com.stanbic.service.multipay.dto.request;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AddLedgerEntriesRequest {

    private Long ledgerId;

    private String ledger;

    private ArrayList<LedgerDetails> ledgerItems;

    private String createdBy;

    }

