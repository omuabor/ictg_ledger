package com.stanbic.service.multipay;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class LedgerApplication {


    public static void main(String[] args) {

        SpringApplication.run(LedgerApplication.class, args);


    }


}
