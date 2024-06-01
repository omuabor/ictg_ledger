package com.stanbic.service.multipay.controller;


import com.stanbic.service.multipay.dto.request.AddLedgerEntriesRequest;
import com.stanbic.service.multipay.dto.request.CreateLedgerRequest;
import com.stanbic.service.multipay.dto.response.GeneralResponse;
import com.stanbic.service.multipay.services.CreateLedgerService;
import com.stanbic.service.multipay.services.nibss.NibssAuthorization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.OK;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LedgerController {

    private final NibssAuthorization nibssService;

    private final CreateLedgerService createLedgerService;

//    private final PaymentBatchService paymentBatchService;

//    @PostMapping("/api/v1/create-ledger")
//    public ResponseEntity<GeneralResponse> submitPaymentBatch(@Valid @RequestBody SubmitPaymentBatchRequest submitPaymentBatchRequest){
//
//       SubmitPaymentBatchResponse batchPaymentResponse = paymentBatchService.submitBatchPayment(submitPaymentBatchRequest);
//
//        return new ResponseEntity<>(new GeneralResponse(_00.getResponseCode(), _00.getResponseMsg(), batchPaymentResponse.getMessage()), OK);
//
//    }

    @PostMapping("/api/v1/ledger/create")
    public ResponseEntity<GeneralResponse> createLedger(@Valid @RequestBody CreateLedgerRequest createLedgerRequest){

        return new ResponseEntity<>(createLedgerService.createLedger(createLedgerRequest), OK);

    }

    @PostMapping("/api/v1/ledger/add-entries")
    public ResponseEntity<GeneralResponse> addLedgerEntries(@Valid @RequestBody AddLedgerEntriesRequest addLedgerEntriesRequest){

        log.info("Add Ledger entries passed to payload {}", addLedgerEntriesRequest);

        return new ResponseEntity<>(createLedgerService.addEntriesToLedger(addLedgerEntriesRequest), OK);

    }
//
//    @GetMapping("/api/v1/ledger/list-ledgers")
//    public ResponseEntity<GeneralResponse> getBatchSummary(@Valid @PathVariable String batchId){
//        return null;
//    }
//
//    @GetMapping()

}
