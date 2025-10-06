package com.example.ncba_case_study.controller;

import com.example.ncba_case_study.model.CustomerRegisterRequest;
import com.example.ncba_case_study.model.VerifyRequest;
import com.example.ncba_case_study.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 8:41 AM
 */
@RestController
@RequestMapping("/api/v1/customers")
public class UserController {

    private final CustomerService customerService;

    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRegisterRequest request) {
        customerService.registerCustomer(request);
        return ResponseEntity.ok("User created");
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyRequest request) {
        customerService.verifyCustomer(request.getEmail(), request.getCode());
        return ResponseEntity.ok("Customer verified and account created.");
    }

}
