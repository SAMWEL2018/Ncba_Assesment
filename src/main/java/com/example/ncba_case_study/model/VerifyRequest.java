package com.example.ncba_case_study.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:34 AM
 */
@Getter
@Setter
public class VerifyRequest {
    private String email;
    private String code;
}
