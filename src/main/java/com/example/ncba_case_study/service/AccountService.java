package com.example.ncba_case_study.service;

import com.example.ncba_case_study.model.Account;
import com.example.ncba_case_study.model.DepositRequest;
import com.example.ncba_case_study.model.DepositResponse;
import com.example.ncba_case_study.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:49 AM
 */
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public DepositResponse deposit(DepositRequest request) {
        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        Account account = accountRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        BigDecimal updatedBalance = account.getBalance().add(request.getAmount());
        account.setBalance(updatedBalance);
        if (updatedBalance.compareTo(BigDecimal.valueOf(500)) >= 0) {
            account.setLoanEligible(true);
        }
        accountRepository.save(account);
        return new DepositResponse(account.getBalance(), account.isLoanEligible());
    }
}
