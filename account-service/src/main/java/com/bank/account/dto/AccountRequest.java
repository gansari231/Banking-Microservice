package com.bank.account.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AccountRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotNull(message = "Balance is required")
    private Double balance;
}