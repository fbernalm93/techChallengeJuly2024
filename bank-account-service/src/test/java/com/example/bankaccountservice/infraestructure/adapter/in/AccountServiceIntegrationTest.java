package com.example.bankaccountservice.infraestructure.adapter.in;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.bankaccountservice.domain.model.Account;
import com.example.bankaccountservice.domain.model.Transaction;

import com.example.bankaccountservice.domain.model.TransactionType;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class AccountServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testAddTransactionIntegration() throws Exception {
        // Arrange
        Account account = new Account();
        account.setInitialBalance(100.0);  // Configura el saldo inicial

        Transaction transaction = new Transaction();
        transaction.setAmount(50.0);
        transaction.setTransactionType(TransactionType.DEPOSITO);

        // Act & Assert
        mockMvc.perform(post("/accounts/" + account.getId() + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 50.0, \"transactionType\": \"DEPOSITO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance", is(150.0)));
    }
}