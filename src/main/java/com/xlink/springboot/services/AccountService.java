package com.xlink.springboot.services;

import com.xlink.springboot.models.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    private final Map<Long, Account> accounts = new HashMap<>();
}
