package com.example.bankaccountservice.infrastructure.adapter.out;

import com.example.bankaccountservice.infrastructure.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
