package com.example.ncba_case_study.repository;

import com.example.ncba_case_study.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:48 AM
 */
@Repository

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(String accountId);
}

