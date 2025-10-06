package com.example.ncba_case_study.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:47 AM
 */
@Getter
@Setter
public class DepositRequest {
    private String accountId;
    private BigDecimal amount;
}
