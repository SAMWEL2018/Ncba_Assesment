package com.example.ncba_case_study.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 10:01 AM
 */
@Getter
@Setter
public class LoanApplicationRequest {
    private String accountId;
    private BigDecimal loanAmount;
    private int tenure; // in months
}
