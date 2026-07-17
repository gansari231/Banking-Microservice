package com.bank.account.mapper;

import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.AccountResponse;
import com.bank.account.entity.Account;

public class AccountMapper {

    public static Account toEntity(AccountRequest request){

        return Account.builder()
                .customerId(request.getCustomerId())
                .accountNumber(request.getAccountNumber())
                .accountType(request.getAccountType())
                .balance(request.getBalance())
                .build();
    }

    public static AccountResponse toResponse(Account account){

        return AccountResponse.builder()
                .id(account.getId())
                .customerId(account.getCustomerId())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .build();
    }
}
