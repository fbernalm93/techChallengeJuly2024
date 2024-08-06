package com.example.clientservice.infrastructure.mapper;

import com.example.clientservice.domain.model.Customer;
import com.example.clientservice.infrastructure.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setGender(customer.getGender());
        customerEntity.setAge(customer.getAge());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setPhoneNumber(customer.getPhoneNumber());
        customerEntity.setPassword(customer.getPassword());
        customerEntity.setState(customer.getState());
        return customerEntity;
    }

    public Customer toDomain(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(customerEntity.getId());
        customer.setName(customerEntity.getName());
        customer.setGender(customerEntity.getGender());
        customer.setAge(customerEntity.getAge());
        customer.setAddress(customerEntity.getAddress());
        customer.setPhoneNumber(customerEntity.getPhoneNumber());
        customer.setPassword(customerEntity.getPassword());
        customer.setState(customerEntity.getState());
        return customer;
    }
}
