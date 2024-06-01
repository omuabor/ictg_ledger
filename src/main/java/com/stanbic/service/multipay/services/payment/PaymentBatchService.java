//package com.stanbic.service.multipay.services.payment;
//
//import com.stanbic.service.multipay.config.webclients.JsonWebClient;
//import com.stanbic.service.multipay.dto.response.SubmitPaymentBatchResponse;
//import com.stanbic.service.multipay.exception.CustomException;
//import com.stanbic.service.multipay.services.nibss.NibssAuthorization;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//
//import static com.stanbic.service.multipay.enums.NibssStatus._003;
//import static com.stanbic.service.multipay.enums.ResponseEnum._99;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PaymentBatchService {
//
//    @Value("${nibss.authorization.client-code}")
//    private String clientCode;
//
//    @Value("${nibss.multipay.submit-batch}")
//    private String batchPaymentUrl;
//
//    @Value("${http.use.proxy}")
//    private boolean useProxy;
//
//    private final NibssAuthorization nibssAuthorization;
//
//    private final JsonWebClient jsonWebClient;
//
//
//    public SubmitPaymentBatchResponse submitBatchPayment(SubmitPaymentBatchRequest batchPaymentRequest) {
//
//        String token = nibssAuthorization.getAccessToken();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("clientCode", clientCode);
//        headers.add("Authorization", "Bearer " + token);
//
//        HashMap<String, Object> response = jsonWebClient.sendJSONRequest(headers, batchPaymentRequest, batchPaymentUrl, useProxy);
//        return retrieveResponseDetails(response);
//    }
//
//    private SubmitPaymentBatchResponse retrieveResponseDetails(HashMap<String, Object> responseBody) {
//
//        SubmitPaymentBatchResponse submitPaymentBatchResponse;
//
//        if (responseBody.get("status") != null) {
//            if (responseBody.get("status").equals(_003.getStatus())) {
//
//                submitPaymentBatchResponse = new SubmitPaymentBatchResponse(
//                        (String) responseBody.get("timestamp"),
//                        (String) responseBody.get("message"),
//                        (String) responseBody.get("status"),
//                        (String) responseBody.get("batchId"),
//                        (Integer) responseBody.get("itemCount"),
//                        (Double) responseBody.get("totalAmount")
//                );
//
//                return submitPaymentBatchResponse;
//
//            } else if (!responseBody.get("status").equals(_003.getStatus()) && responseBody.get("message") != null) {
//                throw new CustomException(_99.getResponseMsg(), (String) responseBody.get("message"));
//            }
//        }else {
//            throw new CustomException(_99.getResponseMsg(), "Payment Batch Operation not successful");
//        }
//
////        MAY do special response for FE here
//
//        throw new CustomException(_99.getResponseMsg(), "Payment Batch Operation not successful");
//
//        }
//
//    }
