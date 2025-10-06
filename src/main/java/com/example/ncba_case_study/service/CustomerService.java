package com.example.ncba_case_study.service;

import com.example.ncba_case_study.model.Account;
import com.example.ncba_case_study.model.Customer;
import com.example.ncba_case_study.model.CustomerRegisterRequest;
import com.example.ncba_case_study.model.VerificationCode;
import com.example.ncba_case_study.repository.AccountRepository;
import com.example.ncba_case_study.repository.CustomerRepository;
import com.example.ncba_case_study.repository.VerificationCodeRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:25 AM
 */
@Getter
@Setter
@Builder
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final VerificationCodeRepository codeRepository;
    private final EmailService emailService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository, VerificationCodeRepository codeRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.codeRepository = codeRepository;
        this.emailService = emailService;
    }

    public String registerCustomer(CustomerRegisterRequest request) {
        // Optionally check for existing email
        customerRepository.findByEmail(request.getEmail()).ifPresent(c -> {
            throw new IllegalStateException("Email already registered");
        });

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .status(Customer.Status.PENDING_VERIFICATION)
                .build();

        Customer saved = customerRepository.save(customer);
        String code = generateVerificationCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10); // expires in 10 minutes

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(saved.getEmail());
        verificationCode.setCode(code);
        verificationCode.setExpiresAt(expiresAt);

        codeRepository.save(verificationCode);
        String emailContent = "Your verification code is: " + code;

        String verificationLink = "https://ncba.com/verify?accountId=" + saved.getAccountId();
        //emailService.sendVerificationEmail(saved.getEmail(), emailContent);
        emailService.sendVerificationEmail(saved.getEmail(), verificationLink + " " + emailContent);
        return  emailContent;
    }

    public Account createAccountForCustomer(Customer customer) {
        Account account = Account.builder()
                .accountId(customer.getAccountId())
                .customer(customer)
                .balance(BigDecimal.ZERO)
                .loanEligible(false)
                .build();
        accountRepository.save(account);
        return account;
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000); // 6-digit code
    }

    public Account verifyCustomer(String email, String code) {
        VerificationCode verification = codeRepository.findByEmailAndCode(email, code)
                .orElseThrow(() -> new IllegalStateException("Invalid or expired verification code."));

        if (verification.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Verification code has expired.");
        }

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Customer not found."));

        customer.setStatus(Customer.Status.VERIFIED);
        customerRepository.save(customer);
        Account account= createAccountForCustomer(customer);
        codeRepository.delete(verification);
        return account;
    }
}
