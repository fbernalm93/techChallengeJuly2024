package com.example.bankaccountservice.infrastructure.adapter.out;

import com.example.bankaccountservice.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCustomerId(String customerId);
}
