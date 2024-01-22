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

}
