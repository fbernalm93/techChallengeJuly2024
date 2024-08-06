package com.example.bankaccountservice.infrastructure.adapter.out;

import com.example.bankaccountservice.domain.model.Account;
import com.example.bankaccountservice.domain.repository.AccountRepository;
import com.example.bankaccountservice.infrastructure.entity.AccountEntity;
import com.example.bankaccountservice.infrastructure.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaAccountRepository implements AccountRepository {

    @Autowired
    private SpringDataAccountRepository springDataAccountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAll() {
        return springDataAccountRepository.findAll()
            .stream()
            .map(accountMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(Long id) {
        return springDataAccountRepository.findById(id)
            .map(accountMapper::toDomain);
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountMapper.toEntity(account);
        AccountEntity savedEntity = springDataAccountRepository.save(accountEntity);
        return accountMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        springDataAccountRepository.deleteById(id);
    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        return springDataAccountRepository.findByCustomerId(customerId)
            .stream()
            .map(accountMapper::toDomain)
            .collect(Collectors.toList());
    }
}
