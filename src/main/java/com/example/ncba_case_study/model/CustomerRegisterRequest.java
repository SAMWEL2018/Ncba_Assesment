package com.example.ncba_case_study.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:23 AM
 */
@Getter
@Setter
@Builder
public class CustomerRegisterRequest {
    private String name;
    private String email;
    private String password;
}
