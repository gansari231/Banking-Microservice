package com.bank.customer.service;

import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.entity.Customer;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);
}
