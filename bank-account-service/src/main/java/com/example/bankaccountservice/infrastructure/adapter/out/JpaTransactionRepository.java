package com.example.bankaccountservice.infrastructure.adapter.out;

import com.example.bankaccountservice.domain.model.Transaction;
import com.example.bankaccountservice.domain.repository.TransactionRepository;
import com.example.bankaccountservice.infrastructure.entity.TransactionEntity;
import com.example.bankaccountservice.infrastructure.adapter.in.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class JpaTransactionRepository implements TransactionRepository {

    @Autowired
    private SpringDataTransactionRepository springDataTransactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<Transaction> findAll() {
        return springDataTransactionRepository.findAll().stream()
            .map(transactionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return springDataTransactionRepository.findById(id)
            .map(transactionMapper::toDomain);
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transaction);
        transactionEntity = springDataTransactionRepository.save(transactionEntity);
        return transactionMapper.toDomain(transactionEntity);
    }

    @Override
    public void deleteById(Long id) {
        springDataTransactionRepository.deleteById(id);
    }

    public void deleteAll() {
        springDataTransactionRepository.deleteAll();
    }
}