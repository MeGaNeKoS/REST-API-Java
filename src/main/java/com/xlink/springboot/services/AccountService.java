package com.xlink.springboot.services;

import com.xlink.springboot.models.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private final Map<Long, Account> accounts = new HashMap<>();
    private Long currentId = 1L;

    public Account getAccount(Long id) {
        return accounts.get(id);
    }


    public Account createAccount(@NotNull Account account) {
        account.setId(currentId++);
        accounts.put(account.getId(), account);
        return account;
    }
}
