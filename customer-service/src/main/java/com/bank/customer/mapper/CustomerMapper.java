package com.bank.customer.mapper;

import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;
import com.bank.customer.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest request) {

        Customer entity = new Customer();
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        return entity;
    }

    public static CustomerResponse toResponse(Customer entity) {

        return CustomerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();
    }
}
