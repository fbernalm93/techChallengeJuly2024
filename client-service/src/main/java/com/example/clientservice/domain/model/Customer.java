package com.example.clientservice.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {
    private String password;
    private Boolean state;

    public Customer(String id, String name, Gender gender, Integer age, String address, String phoneNumber, String password, Boolean state) {
        super(id, name, gender, age, address, phoneNumber);
        this.password = password;
        this.state = state;
    }

    public Customer() {}
}
