package com.example.bankaccountservice.infrastructure.mapper;

import com.example.bankaccountservice.domain.model.Account;
import com.example.bankaccountservice.domain.model.Transaction;
import com.example.bankaccountservice.infrastructure.entity.AccountEntity;
import com.example.bankaccountservice.infrastructure.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setInitialBalance(account.getInitialBalance());
        accountEntity.setState(account.isState());
        accountEntity.setCustomerId(account.getCustomerId());
        accountEntity.setTransactions(
                account.getTransactions() != null ?
                        account.getTransactions().stream()
                                .map(transaction -> toEntity(transaction, accountEntity))
                                .collect(Collectors.toList()) :
                        new ArrayList<>()
        );
        return accountEntity;
    }

    public Account toDomain(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }

        Account account = new Account();
        account.setId(accountEntity.getId());
        account.setAccountNumber(accountEntity.getAccountNumber());
        account.setAccountType(accountEntity.getAccountType());
        account.setInitialBalance(accountEntity.getInitialBalance());
        account.setState(accountEntity.isState());
        account.setCustomerId(accountEntity.getCustomerId());
        account.setTransactions(
                accountEntity.getTransactions() != null ?
                        accountEntity.getTransactions().stream()
                                .map(transactionEntity -> toDomain(transactionEntity, account))
                                .collect(Collectors.toList()) :
                        new ArrayList<>()
        );
        return account;
    }

    private TransactionEntity toEntity(Transaction transaction, AccountEntity accountEntity) {
        if (transaction == null) {
            return null;
        }

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transaction.getId());
        transactionEntity.setDate(transaction.getDate());
        transactionEntity.setTransactionType(transaction.getTransactionType());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setBalance(transaction.getBalance());
        transactionEntity.setAccount(accountEntity);
        return transactionEntity;
    }

    private Transaction toDomain(TransactionEntity transactionEntity, Account account) {
        if (transactionEntity == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setId(transactionEntity.getId());
        transaction.setDate(transactionEntity.getDate());
        transaction.setTransactionType(transactionEntity.getTransactionType());
        transaction.setAmount(transactionEntity.getAmount());
        transaction.setBalance(transactionEntity.getBalance());
        transaction.setAccount(account);
        return transaction;
    }

    private TransactionEntity toEntity(Transaction transaction) {
        return toEntity(transaction, transaction.getAccount() != null ? toEntity(transaction.getAccount()) : null);
    }

    private Transaction toDomain(TransactionEntity transactionEntity) {
        return toDomain(transactionEntity, transactionEntity.getAccount() != null ? toDomain(transactionEntity.getAccount()) : null);
    }
}

