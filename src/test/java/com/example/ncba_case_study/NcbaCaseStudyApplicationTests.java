package com.example.ncba_case_study;

import com.example.ncba_case_study.model.*;
import com.example.ncba_case_study.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class NcbaCaseStudyApplicationTests {
    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
    }
    @Test
    public void registerCustomer() {
        CustomerRegisterRequest req = CustomerRegisterRequest.builder()
                .name("James")
                .email("james@ncba.com")
                .password("root")
                .build();
        try {
            CustomResponse response = customerService.registerCustomer(req);
            log.info("registration response {}", new ObjectMapper().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void customerVerification(){
        VerifyRequest request= VerifyRequest.builder()
                .email("jame@ncba.com")
                .code("389106")
                .build();
        try {
            CustomResponse response= customerService.verifyCustomer(request.getEmail(), request.getCode());
            log.info("Account response {}",new ObjectMapper().writeValueAsString(response));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
