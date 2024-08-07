package com.example.bankaccountservice.infrastructure.adapter.in;

import com.example.bankaccountservice.domain.model.Transaction;
import com.example.bankaccountservice.infrastructure.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setDate(transaction.getDate());
        transactionEntity.setTransactionType(transaction.getTransactionType());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setBalance(transaction.getBalance());
        // Asignación de la cuenta si es necesario
        if (transaction.getAccount() != null) {
            transactionEntity.setAccount(new AccountMapper().toEntity(transaction.getAccount()));
        }
        return transactionEntity;
    }

    public Transaction toDomain(TransactionEntity transactionEntity) {
        if (transactionEntity == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactionEntity.getId());
        transaction.setDate(transactionEntity.getDate());
        transaction.setTransactionType(transactionEntity.getTransactionType());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setBalance(transactionEntity.getBalance());
        // Asignación de la cuenta si es necesario
        if (transactionEntity.getAccount() != null) {
            transaction.setAccount(new AccountMapper().toDomain(transactionEntity.getAccount()));
        }
        return transaction;
    }
}
