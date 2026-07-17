package com.bank.customer.service.impl;

import com.bank.customer.dto.CustomerPatchRequest;
import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.entity.Customer;
import com.bank.customer.exception.CustomerAlreadyExistsException;
import com.bank.customer.exception.CustomerNotFoundException;
import com.bank.customer.mapper.CustomerMapper;
import com.bank.customer.repository.CustomerRepository;
import com.bank.customer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        customerRepository.findByEmail(request.getEmail())
                .ifPresent(customer -> {
                    throw new CustomerAlreadyExistsException("Customer with email " + request.getEmail() + " already exists.");
        });

        Customer customer = CustomerMapper.toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll().stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(CustomerMapper::toResponse)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        customerRepository.findByEmail(request.getEmail())
                .ifPresent(existingCustomer -> {;
                    if (!existingCustomer.getId().equals(id)) {
                        throw new CustomerAlreadyExistsException("Customer with email " + request.getEmail() + " already exists.");
                    }
                });

        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());

        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponse(updatedCustomer);
    }

    @Override
    public CustomerResponse patchCustomer(Long id, CustomerPatchRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));

        if (request.getEmail() != null) {

            customerRepository.findByEmail(request.getEmail())
                    .ifPresent(existingCustomer -> {

                        if (!existingCustomer.getId().equals(id)) {
                            throw new CustomerAlreadyExistsException(
                                    "Customer already exists with email: "
                                            + request.getEmail());
                        }

                    });

            customer.setEmail(request.getEmail());
        }


        if(request.getName() != null) {
            customer.setName(request.getName());
        }

        if(request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }

        if(request.getPhone() != null) {
            customer.setPhone(request.getPhone());
        }

        Customer updatedCustomer =
                customerRepository.save(customer);

        return CustomerMapper.toResponse(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id: " + id));

        customerRepository.delete(customer);

    }
}
