package com.stanbic.service.multipay.entity;

import com.stanbic.service.multipay.dto.request.LedgerDetails;
import com.stanbic.service.multipay.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "ICTG_T_LEDGER_ENTRIES")
@Data
public class LedgerEntries {

    @Id
    @GeneratedValue(generator = "ICTG_T_LEDGER_ENTRIES_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ICTG_T_LEDGER_ENTRIES_GEN", sequenceName = "ICTG_T_LEDGER_ENTRIES_SEQ",
            initialValue = 1001, allocationSize = 1)
    private Long id;

    private Long ledgerId;

    private String ledger;

    private LocalDateTime entryTime;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    private String transactionDate;

    private Double amount;

    private String particulars;

    private String createdBy;


}
