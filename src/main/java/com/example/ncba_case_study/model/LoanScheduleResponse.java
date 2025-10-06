package com.example.ncba_case_study.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 10:01 AM
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanScheduleResponse {
    private BigDecimal loanAmount;
    private int tenure;
    private BigDecimal monthlyInstallment;
    private BigDecimal totalRepayable;
    private BigDecimal interestRate;
}
