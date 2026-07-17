package com.bank.customer.service;

import com.bank.customer.dto.CustomerPatchRequest;
import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);
    CustomerResponse updateCustomer(Long id, CustomerRequest request);
    CustomerResponse patchCustomer(Long id, CustomerPatchRequest request);
    void deleteCustomer(Long id);

}
