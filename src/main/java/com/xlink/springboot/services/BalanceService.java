package com.xlink.springboot.services;

import com.xlink.springboot.Sample;
import com.xlink.springboot.util.BalanceResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    public BalanceResponse getAggregatedBalance(String sampleAccountName, String targetAccountNo) {
        Sample.SampleAccount selectedSampleAccount = findSampleAccountByName(sampleAccountName);
        if (selectedSampleAccount == null) {
            return null;
        }

        Map<String, Sample.Account> accountMap = buildAccountMap(selectedSampleAccount.getAccounts());

        return buildBalanceResponse(targetAccountNo, accountMap);
    }

    private BalanceResponse buildBalanceResponse(String accountNo, Map<String, Sample.Account> accountMap) {
        Sample.Account account = accountMap.get(accountNo);
        List<BalanceResponse> childResponses = new ArrayList<>();

        // TODO: It seem newer version of Java prefer immutable approach instead. WIll update this later.
        int totalBalance = account.getBalance();
        for (Sample.Account childAccount : accountMap.values()) {
            if (childAccount.getParentAccountNo().equals(accountNo)) {
                BalanceResponse childResponse = buildBalanceResponse(childAccount.getAccountNo(), accountMap);
                totalBalance += childResponse.getBalance();
                childResponses.add(childResponse);
            }
        }

        return new BalanceResponse(account.getAccountNo(), totalBalance, childResponses.isEmpty() ? null : childResponses);
    }

    private Sample.SampleAccount findSampleAccountByName(String sampleAccountName) {
        for (Sample.SampleAccount sampleAccount : Sample.SAMPLE_ACCOUNTS) {
            if (sampleAccount.getSampleName().equals(sampleAccountName)) {
                return sampleAccount;
            }
        }
        return null;
    }

    private Map<String, Sample.Account> buildAccountMap(Sample.Account[] accounts) {
        Map<String, Sample.Account> accountMap = new HashMap<>();
        for (Sample.Account account : accounts) {
            accountMap.put(account.getAccountNo(), account);
        }
        return accountMap;
    }

    private int calculateAggregateBalance(String accountNo, Map<String, Sample.Account> accountMap) {
        // TODO: It seem newer version of Java prefer immutable approach instead. WIll update this later.
        int totalBalance = 0;
        for (Sample.Account account : accountMap.values()) {
            if (account.getParentAccountNo().equals(accountNo)) {
                int accountBalance = account.getBalance() + calculateAggregateBalance(account.getAccountNo(), accountMap);
                totalBalance += accountBalance;
            }
        }
        return totalBalance;
    }
}


