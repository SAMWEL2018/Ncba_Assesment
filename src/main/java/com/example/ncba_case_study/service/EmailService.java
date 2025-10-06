package com.example.ncba_case_study.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:24 AM
 */
@Service
public class EmailService {
    @Async
    public void sendVerificationEmail(String email, String verificationLink) {
        System.out.println("Sending verification email to " + email);
        System.out.println("Verification link: " + verificationLink);
    }
}
