package com.example.clientservice.infrastructure.adapter.out;

import com.example.clientservice.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerEntity, String> {
}
