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

    public Account updateAccount(Long id, @NotNull Account account) {
        Account existingAccount = accounts.get(id);
        if (existingAccount == null) {
            return null;
        }
        existingAccount.setName(account.getName());
        existingAccount.setDescription(account.getDescription());
        return existingAccount;
    }

    public void deleteAccount(Long id) {
        accounts.remove(id);
    }
}
