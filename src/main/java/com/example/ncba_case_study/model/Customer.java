package com.example.ncba_case_study.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * @author samwel.wafula
 * Created on 10/6/2025
 * Time 9:22 AM
 */
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING_VERIFICATION,
        VERIFIED
    }

    @PrePersist
    public void generateAccountId() {
        this.accountId = "ACC-" + UUID.randomUUID().toString();
    }
}
