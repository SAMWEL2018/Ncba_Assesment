package com.example.ncba_case_study.service;

import com.example.ncba_case_study.model.*;
import com.example.ncba_case_study.repository.AccountRepository;
import com.example.ncba_case_study.repository.CustomerRepository;
import com.example.ncba_case_study.repository.VerificationCodeRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository, VerificationCodeRepository codeRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.codeRepository = codeRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomResponse registerCustomer(CustomerRegisterRequest request) {
        // Optionally check for existing email
        CustomResponse response = CustomResponse.builder().build();
        Optional<Customer> existingCustomer = customerRepository.findByEmail(request.getEmail());

        if (existingCustomer.isPresent()) {
            return CustomResponse.builder()
                    .responseCode("200")
                    .responseMessage("Email already registered")
                    .build();
        }

        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
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
        response.setResponseCode("200");
        response.setResponseMessage("success");
        response.setData(emailContent);
        return response;
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

    public CustomResponse verifyCustomer(String email, String code) {
        Optional<VerificationCode> verification = codeRepository.findByEmailAndCode(email, code);
        if (verification.isEmpty()) {
            return CustomResponse.builder()
                    .responseCode("400")
                    .responseMessage("Invalid or expired verification code.")
                    .build();
        }

        if (verification.get().getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Verification code has expired.");
        }

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Customer not found."));

        customer.setStatus(Customer.Status.VERIFIED);
        customerRepository.save(customer);
        Account account = createAccountForCustomer(customer);
        account.setCustomer(null);
        codeRepository.delete(verification.get());

        return CustomResponse.builder()
                .responseCode("200")
                .responseMessage("account created")
                .account(account)
                .build();
    }
}
