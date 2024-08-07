package com.example.bankaccountservice.application.service;

import com.example.bankaccountservice.domain.exception.InsufficientBalanceException;
import com.example.bankaccountservice.domain.model.Account;
import com.example.bankaccountservice.domain.model.Transaction;
import com.example.bankaccountservice.domain.model.TransactionType;
import com.example.bankaccountservice.domain.repository.AccountRepository;
import com.example.bankaccountservice.infrastructure.adapter.out.JpaTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The type Account service test.
 */
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private JpaTransactionRepository jpaTransactionRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Unit test to addTransaction method. Verify successful implementation to add transactions - happyTest
     */
    @Test
    public void testAddTransaction() {
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setInitialBalance(100.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(50.0);
        transaction.setTransactionType(TransactionType.DEPOSITO);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(jpaTransactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction result = accountService.addTransaction(accountId, transaction);

        assertNotNull(result);
        assertEquals(150.0, result.getBalance());
        verify(accountRepository, times(1)).findById(accountId);
        verify(jpaTransactionRepository, times(1)).save(transaction);
    }

    /**
     * Unit test to testAddTransactionInsufficientBalance. Verify successful implementation to add transactions with insufficient balance - not happyTest
     */
    @Test
    public void testAddTransactionInsufficientBalance() {
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setInitialBalance(100.0);

        Transaction transaction = new Transaction();
        transaction.setAmount(150.0);
        transaction.setTransactionType(TransactionType.RETIRO);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> accountService.addTransaction(accountId, transaction));
        verify(accountRepository, times(1)).findById(accountId);
        verify(jpaTransactionRepository, times(0)).save(transaction);
    }
}
