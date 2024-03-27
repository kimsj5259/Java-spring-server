package com.finance.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.common.ApiResponse;
import com.finance.model.Order;
import com.finance.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/quote")
    public ApiResponse transferQuote(@RequestBody BigDecimal amount, String targetCurrency) {
        return transferService.calculateQuote(amount, targetCurrency);
    }

    @PostMapping("/request")
    public ApiResponse transferRequest(@RequestBody Order orderReqeust) {
        return transferService.sendingMoney(orderReqeust);
    }

}