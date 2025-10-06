package com.example.ncba_case_study.repository;

import com.example.ncba_case_study.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:36 AM
 */
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findByEmailAndCode(String email, String code);
}
