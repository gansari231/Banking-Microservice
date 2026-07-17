package com.bank.account.service;

import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountRequest request);
    List<AccountResponse> getAllAccounts();
    AccountResponse getById(Long id);
    List<AccountResponse> getByCustomerId(Long customerId);
}