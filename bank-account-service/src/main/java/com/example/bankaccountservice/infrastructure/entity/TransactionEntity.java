package com.example.bankaccountservice.infrastructure.entity;

import com.example.bankaccountservice.domain.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name="transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(name="amount", nullable = false)
    private double amount;

    @Column(name="balance", nullable = false)
    private double balance;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;
}
