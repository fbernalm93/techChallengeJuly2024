package com.example.clientservice.infrastructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "customerId")
public class CustomerEntity extends PersonEntity {
    @NotEmpty(message = "Field is required")
    @Column(name = "password")
    private String password;

    @Column(name = "state")
    private Boolean state;
}
