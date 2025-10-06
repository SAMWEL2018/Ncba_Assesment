package com.example.ncba_case_study.controller;

import com.example.ncba_case_study.model.DepositRequest;
import com.example.ncba_case_study.model.DepositResponse;
import com.example.ncba_case_study.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:50 AM
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private final AccountService accountService;
   /*
   Deposit Endpoint
   * */

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestBody DepositRequest request) {
        DepositResponse response = accountService.deposit(request);
        return ResponseEntity.ok(response);
    }
}
