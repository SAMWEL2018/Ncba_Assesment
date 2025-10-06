package com.example.ncba_case_study.repository;

import com.example.ncba_case_study.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:24 AM
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}