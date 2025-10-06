package com.example.ncba_case_study.service;

import com.example.ncba_case_study.model.Account;
import com.example.ncba_case_study.model.LoanApplicationRequest;
import com.example.ncba_case_study.model.LoanScheduleResponse;
import com.example.ncba_case_study.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 10:03 AM
 */
@Service
public class LoanService {
    private final AccountRepository accountRepository;
    private final CoreBankingService coreBankingService;

    public LoanService(AccountRepository accountRepository, CoreBankingService coreBankingService) {
        this.accountRepository = accountRepository;
        this.coreBankingService = coreBankingService;
    }


    public LoanScheduleResponse applyForLoan(LoanApplicationRequest request) {
        Account account = accountRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getBalance().compareTo(BigDecimal.valueOf(500)) < 0) {
            throw new IllegalStateException("Account balance must be at least KES 500 to apply for a loan.");
        }

        BigDecimal amount = request.getLoanAmount();

        if (amount.compareTo(BigDecimal.valueOf(1000)) < 0 ||
                amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            throw new IllegalArgumentException("Loan amount must be between KES 1,000 and 10,000.");
        }

        return coreBankingService.generateLoanSchedule(amount, request.getTenure());
    }
}
