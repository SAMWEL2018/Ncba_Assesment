package com.example.ncba_case_study.model;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:47 AM
 */
@Builder
@NoArgsConstructor
@Getter
@Setter
public class DepositResponse {
    private BigDecimal newBalance;
    private boolean loanEligible;

    public DepositResponse(BigDecimal newBalance, boolean loanEligible) {
        this.newBalance = newBalance;
        this.loanEligible = loanEligible;
    }
}
