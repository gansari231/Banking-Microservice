package com.bank.customer.service;

import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);
}
