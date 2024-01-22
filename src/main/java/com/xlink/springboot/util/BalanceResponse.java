package com.xlink.springboot.util;

import lombok.Getter;

import java.util.List;

@Getter
public class BalanceResponse {
    private final String accountName;
    private final int balance;
    private final List<BalanceResponse> childs;

    public BalanceResponse(String accountName, int balance, List<BalanceResponse> childs) {
        this.accountName = accountName;
        this.balance = balance;
        this.childs = childs;
    }

}
