package com.nsr.digitalbanking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.service.BankAccountService;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BankAccountController {

    private BankAccountService service;

    @GetMapping("/")
    public List<BankAccountDTO> getAllBankAccounts() {
        return service.getAllBankAccounts();
    }
}
