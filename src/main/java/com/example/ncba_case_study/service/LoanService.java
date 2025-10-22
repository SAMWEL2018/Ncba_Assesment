package com.example.ncba_case_study.service;

import com.example.ncba_case_study.model.*;
import com.example.ncba_case_study.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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


    public CustomResponse applyForLoan(LoanApplicationRequest request) {
        Optional<Account> account = accountRepository.findByAccountId(request.getAccountId());
        if (account.isEmpty()){
            return CustomResponse.builder()
                    .responseCode("400")
                    .responseMessage("Account not found")
                    .build();
        }

        if (account.get().getBalance().compareTo(BigDecimal.valueOf(500)) < 0) {
            return CustomResponse.builder().responseCode("400").responseMessage("Account balance must be at least KES 500 to apply for a loan.").build();
        }

        BigDecimal amount = request.getLoanAmount();

        if (amount.compareTo(BigDecimal.valueOf(1000)) < 0 ||
                amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            return CustomResponse.builder().responseCode("400").responseMessage("Loan amount must be between KES 1,000 and 10,000.").build();
        }

        return CustomResponse.builder().responseCode("200").responseMessage("Success Loan")
                .loanScheduleResponse(coreBankingService.generateLoanSchedule(amount, request.getTenure()))
                .build();

    }
}
