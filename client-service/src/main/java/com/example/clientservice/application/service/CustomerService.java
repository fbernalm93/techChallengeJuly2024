package com.example.clientservice.application.service;

import com.example.clientservice.domain.model.Customer;
import com.example.clientservice.infrastructure.entity.CustomerEntity;
import com.example.clientservice.infrastructure.mapper.CustomerMapper;
import com.example.clientservice.infrastructure.adapter.out.SpringDataCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private SpringDataCustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDomain);
    }

    public Customer createCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        customerEntity = customerRepository.save(customerEntity);
        return customerMapper.toDomain(customerEntity);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
