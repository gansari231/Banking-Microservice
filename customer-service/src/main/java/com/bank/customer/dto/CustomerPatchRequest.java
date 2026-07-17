package com.bank.customer.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CustomerPatchRequest {

    private String name;

    @Email(message = "Invalid email")
    private String email;

    private String phone;

    private String address;

}