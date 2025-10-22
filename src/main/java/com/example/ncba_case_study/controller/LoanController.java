package com.example.ncba_case_study.controller;

import com.example.ncba_case_study.model.CustomResponse;
import com.example.ncba_case_study.model.LoanApplicationRequest;
import com.example.ncba_case_study.model.LoanScheduleResponse;
import com.example.ncba_case_study.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 10:04 AM
 */
@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    /*Loan Endpoint */
    @PostMapping("/apply")
    public ResponseEntity<CustomResponse> applyForLoan(@RequestBody LoanApplicationRequest request) {
        CustomResponse response = loanService.applyForLoan(request);
        return ResponseEntity.status(Integer.parseInt(response.getResponseCode())).body(response);
    }
}
