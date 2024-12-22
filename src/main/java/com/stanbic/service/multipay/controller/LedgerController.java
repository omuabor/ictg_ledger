package com.stanbic.service.multipay.controller;


import com.stanbic.service.multipay.dto.request.AddLedgerEntriesRequest;
import com.stanbic.service.multipay.dto.request.CreateLedgerRequest;
import com.stanbic.service.multipay.dto.response.GeneralResponse;
import com.stanbic.service.multipay.services.CreateLedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/ledger")
public class LedgerController {
    private final CreateLedgerService createLedgerService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createLedger(@Valid @RequestBody CreateLedgerRequest createLedgerRequest){
        return new ResponseEntity<>(createLedgerService.createLedger(createLedgerRequest), OK);

    }

    @PostMapping("/entries")
    public ResponseEntity<GeneralResponse> addLedgerEntries(@Valid @RequestBody AddLedgerEntriesRequest addLedgerEntriesRequest){

        log.info("Add Ledger entries passed to payload {}", addLedgerEntriesRequest);

        return new ResponseEntity<>(createLedgerService.addEntriesToLedger(addLedgerEntriesRequest), OK);

    }
//
    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllLedgers(){
        return new ResponseEntity<>(createLedgerService.getAllLedgersOpened(), OK);
    }

}
