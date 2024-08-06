package com.example.bankaccountservice.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Account {
    private Long id;
    private String accountNumber;
    private AccountType accountType;
    private double initialBalance;
    private boolean state;
    private String customerId;
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }
}
