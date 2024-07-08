package com.example.bankaccountservice.application.client;

import com.example.bankaccountservice.application.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.url}")
    private String customerServiceUrl;

    public CustomerDTO getCustomerById(String customerId) {
        return restTemplate.getForObject(customerServiceUrl + "/" + customerId, CustomerDTO.class);
    }
}
