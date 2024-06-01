package com.stanbic.service.multipay.services.nibss;


import com.stanbic.service.multipay.config.webclients.UrlEncodedWebClient;
import com.stanbic.service.multipay.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class NibssAuthorization {



    @Value("${nibbs.authorization.url}")
    private String authorizationUrl;
    @Value("${http.use.proxy}")
    private boolean useProxy;

    private final UrlEncodedWebClient urlEncodedWebClient;

    public String getAccessToken() {
        try {

            HashMap<String, Object> response = urlEncodedWebClient.sendUrlEncodedRequest(authorizationUrl, useProxy);

            log.info("response from token call : {}", response);
            return retrieveAccessToken(response);
        }
        catch (Exception e){
            throw new CustomException("Could not authorize NIBSS request", "Failure in authorizing NIBSS request");
        }
    }

    private String retrieveAccessToken(HashMap<String, Object> responseBody){
        String accesstoken;

        if (responseBody.get("access_token") != null) {
            accesstoken = (String) responseBody.get("access_token");
        } else {
            throw new CustomException("Could not authorize NIBSS request", "Failure in authorizing NIBSS request");
        }

        return accesstoken;

    }
}
