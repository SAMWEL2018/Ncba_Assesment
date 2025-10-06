package com.example.ncba_case_study.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanScheduleResponse {
    private String responseCode;
    private String responseMessage;
    private BigDecimal loanAmount;
    private Integer tenure;
    private BigDecimal monthlyInstallment;
    private BigDecimal totalRepayable;
    private BigDecimal interestRate;
}
