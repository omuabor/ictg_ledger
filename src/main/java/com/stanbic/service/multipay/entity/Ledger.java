package com.stanbic.service.multipay.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ICTG_S_LEDGER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ledger {

    @Id
    @GeneratedValue(generator = "ICTG_S_LEDGER_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ICTG_S_LEDGER_GEN", sequenceName = "ICTG_S_LEDGER_SEQ",
            initialValue = 1001, allocationSize = 1)
    private Long id;

    private String ledgerType;

    private String createdBy;

    private String createTime;

    private String description;

}
