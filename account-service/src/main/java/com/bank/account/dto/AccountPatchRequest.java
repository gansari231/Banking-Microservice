package com.bank.account.dto;

import lombok.Data;

@Data
public class AccountPatchRequest {

    private String accountType;
    private Double balance;
}