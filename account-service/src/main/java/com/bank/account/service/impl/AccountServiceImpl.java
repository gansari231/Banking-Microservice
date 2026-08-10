package com.bank.account.service.impl;

import com.bank.account.client.CustomerClient;
import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.AccountResponse;
import com.bank.account.dto.CustomerResponse;
import com.bank.account.entity.Account;
import com.bank.account.exception.*;
import com.bank.account.mapper.AccountMapper;
import com.bank.account.repository.AccountRepository;
import com.bank.account.service.AccountService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerClient customerClient;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerClient customerClient) {

        this.accountRepository = accountRepository;
        this.customerClient = customerClient;
    }

    @Override
    public AccountResponse createAccount(AccountRequest request) {

        if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new AccountAlreadyExistsException("Account with number " + request.getAccountNumber() + " already exists.");
        }

        CustomerResponse customer;

        try {
            customer = customerClient.getCustomerById(request.getCustomerId());
        }
        catch (FeignException.NotFound ex) {
            throw new CustomerNotFoundException("Customer with ID " + request.getCustomerId() + " not found.");
        }

        Account account = AccountMapper.toEntity(request);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toResponse(savedAccount);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::toResponse)
                .toList();
    }

    @Override
    public AccountResponse getById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toResponse)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found."));
    }

    @Override
    public List<AccountResponse> getByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(AccountMapper::toResponse)
                .toList();
    }
}