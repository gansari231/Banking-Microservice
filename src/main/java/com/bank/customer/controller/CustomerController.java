package com.bank.customer.controller;

import com.bank.customer.dto.CustomerPatchRequest;
import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.entity.Customer;
import com.bank.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) {

        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {

        return customerService.updateCustomer(id, request);
    }

    @PatchMapping("/{id}")
    public CustomerResponse patchCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerPatchRequest request) {

        return customerService.patchCustomer(id, request);

    }
}
