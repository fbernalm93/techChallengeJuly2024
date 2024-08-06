package com.example.bankaccountservice.infrastructure.entity;

import com.example.bankaccountservice.domain.model.AccountType;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="account_number", nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="account_type", nullable = false)
    private AccountType accountType;

    @Column(name="initial_balance", nullable = false)
    private double initialBalance;

    @Column(name="state", nullable = false)
    @ColumnDefault("true")
    private boolean state;

    @Column(name="customer_id", nullable = false)
    private String customerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions = new ArrayList<>();
}
