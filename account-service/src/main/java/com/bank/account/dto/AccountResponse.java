package com.bank.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {

    private Long id;

    private Long customerId;

    private String accountNumber;

    private String accountType;

    private Double balance;
}
