package com.example.ncba_case_study.model;

import lombok.*;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:34 AM
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyRequest {
    private String email;
    private String code;
}
