package com.example.ncba_case_study.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 11:47 AM
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {
    private String responseCode;
    public String responseMessage;
    private String data;
    private Account account;
    private LoanScheduleResponse loanScheduleResponse;
}
