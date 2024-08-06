package com.example.clientservice.infrastructure.adapter.out;

import com.example.clientservice.domain.model.Customer;
import com.example.clientservice.domain.repository.CustomerRepository;
import com.example.clientservice.infrastructure.entity.CustomerEntity;
import com.example.clientservice.infrastructure.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    @Autowired
    private SpringDataCustomerRepository springDataCustomerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> findAll() {
        return springDataCustomerRepository.findAll().stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(String id) {
        return springDataCustomerRepository.findById(id)
                .map(customerMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        customerEntity = springDataCustomerRepository.save(customerEntity);
        return customerMapper.toDomain(customerEntity);
    }

    @Override
    public void deleteById(String id) {
        springDataCustomerRepository.deleteById(id);
    }
}
