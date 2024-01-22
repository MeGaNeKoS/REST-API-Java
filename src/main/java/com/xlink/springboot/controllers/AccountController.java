package com.xlink.springboot.controllers;

import com.xlink.springboot.exceptions.CrudException;
import com.xlink.springboot.models.Account;
import com.xlink.springboot.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Account> getAccount(@RequestParam Long id) throws CrudException {
        Account account = accountService.getAccount(id);
        if (account == null) {
            throw new CrudException(CrudException.ExceptionType.DataNotFound, String.format("Account with id %d not found.", id));
        }

        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestParam Long id, @RequestBody Account account) throws CrudException {
        Account updatedAccount = accountService.updateAccount(id, account);
        if (updatedAccount == null) {
            throw new CrudException(CrudException.ExceptionType.DataNotFound, String.format("Account with id %d not found.", id));
        }
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@RequestParam Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}
