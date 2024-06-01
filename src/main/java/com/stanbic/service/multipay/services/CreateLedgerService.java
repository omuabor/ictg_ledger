package com.stanbic.service.multipay.services;

import com.stanbic.service.multipay.dto.request.AddLedgerEntriesRequest;
import com.stanbic.service.multipay.dto.request.CreateLedgerRequest;
import com.stanbic.service.multipay.dto.request.LedgerDetails;
import com.stanbic.service.multipay.dto.response.CreateLedgerResponse;
import com.stanbic.service.multipay.dto.response.GeneralResponse;
import com.stanbic.service.multipay.entity.Ledger;
import com.stanbic.service.multipay.entity.LedgerEntries;
import com.stanbic.service.multipay.enums.TransactionType;
import com.stanbic.service.multipay.repository.LedgerEntriesRepository;
import com.stanbic.service.multipay.repository.LedgerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.stanbic.service.multipay.enums.ResponseEnum._00;

@Service
@AllArgsConstructor
@Slf4j
public class CreateLedgerService {

    private final LedgerRepository ledgerRepository;

    private final LedgerEntriesRepository ledgerEntriesRepository;

    public GeneralResponse createLedger(CreateLedgerRequest createLedgerRequest){

        Ledger ledger = new Ledger();
        ledger.setCreatedBy(createLedgerRequest.getCreatedBy());
        ledger.setLedgerType(createLedgerRequest.getLedgerName());
        ledger.setCreateTime(LocalDateTime.now().toString());
        ledger.setDescription(createLedgerRequest.getDescription());

        ledger = ledgerRepository.save(ledger);

        HashMap<String, Object> responseDetails = new HashMap<>();

        responseDetails.put("ledger", ledger);

         return new GeneralResponse(_00.getResponseCode(), _00.getResponseMsg(), responseDetails);

    }

    public GeneralResponse addEntriesToLedger(AddLedgerEntriesRequest addLedgerEntriesRequest) {

        Ledger openedLedger = ledgerRepository.findById(addLedgerEntriesRequest.getLedgerId()).orElseThrow(
                ()-> {return new RuntimeException("Ledger passed not found");}
        );

        log.info("Add ledger entries passed {}", addLedgerEntriesRequest);

        ArrayList<LedgerDetails> ledgerItems = addLedgerEntriesRequest.getLedgerItems();

        List<LedgerEntries> ledgerEntries = new ArrayList<>();

        ledgerItems.forEach(
                (ledgerItem) ->{
                    LedgerEntries ledgerEntry = new LedgerEntries();
                    ledgerEntry.setAmount(ledgerItem.getAmount());
                    ledgerEntry.setTransactionType(ledgerItem.getTransactionType());
                    ledgerEntry.setParticulars(ledgerItem.getParticulars());
                    ledgerEntry.setTransactionDate(ledgerItem.getTransactionDate());
                    ledgerEntry.setEntryTime(LocalDateTime.now());
                    ledgerEntry.setCreatedBy(addLedgerEntriesRequest.getCreatedBy());
                    ledgerEntry.setLedger(addLedgerEntriesRequest.getLedger());
                    ledgerEntry.setLedgerId(addLedgerEntriesRequest.getLedgerId());
                    ledgerEntries.add(ledgerEntry);
                }
        );

        ledgerEntriesRepository.saveAll(ledgerEntries);

        HashMap<String, Object> responseDetails = new HashMap<>();

        responseDetails.put("status", "Ledger entries added successfully");

        return new GeneralResponse(_00.getResponseCode(), _00.getResponseMsg(), responseDetails);

    }
}
