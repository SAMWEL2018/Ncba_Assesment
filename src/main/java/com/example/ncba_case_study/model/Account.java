package com.example.ncba_case_study.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:46 AM
 */
@Entity
@Table(name = "accounts")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    private boolean loanEligible = false;
}
