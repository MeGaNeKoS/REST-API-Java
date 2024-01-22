package com.xlink.springboot.controllers;

import com.xlink.springboot.services.BalanceService;
import com.xlink.springboot.util.BalanceResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/aggregate")
    public BalanceResponse getAggregateBalance(@RequestParam String sampleAccount,
                                               @RequestParam String targetAccount) {
        return balanceService.getAggregatedBalance(sampleAccount, targetAccount);
    }
}
