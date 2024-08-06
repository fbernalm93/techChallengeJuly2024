package com.example.bankaccountservice.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private LocalDateTime date;
    private TransactionType transactionType;
    private double amount;
    private double balance;

    @JsonBackReference
    private Account account;
}
