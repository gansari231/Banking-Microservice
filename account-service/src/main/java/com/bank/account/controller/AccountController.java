package com.bank.account.controller;

import com.bank.account.dto.AccountRequest;
import com.bank.account.dto.AccountResponse;
import com.bank.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountResponse> getAccountsByCustomerId(@PathVariable Long customerId) {
        return accountService.getByCustomerId(customerId);
    }
}