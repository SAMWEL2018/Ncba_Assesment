package com.example.ncba_case_study.service;

import com.example.ncba_case_study.model.LoanScheduleResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 10:02 AM
 */
@Service
public class CoreBankingService {
    public LoanScheduleResponse generateLoanSchedule(BigDecimal loanAmount, int tenure) {
        BigDecimal interestRate = new BigDecimal("0.10");

        BigDecimal interest = loanAmount.multiply(interestRate);
        BigDecimal totalRepayable = loanAmount.add(interest);
        BigDecimal monthlyInstallment = totalRepayable.divide(BigDecimal.valueOf(tenure), 2, RoundingMode.HALF_UP);

        return new LoanScheduleResponse(
                loanAmount,
                tenure,
                monthlyInstallment,
                totalRepayable,
                interestRate
        );
    }
}
