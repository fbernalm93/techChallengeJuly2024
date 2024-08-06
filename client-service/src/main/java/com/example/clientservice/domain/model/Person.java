package com.example.clientservice.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Person {
    private String id;
    private String name;
    private Gender gender;
    private Integer age;
    private String address;
    private String phoneNumber;

    public Person(String id, String name, Gender gender, Integer age, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Person() {}
}
