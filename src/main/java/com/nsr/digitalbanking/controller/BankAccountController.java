package com.nsr.digitalbanking.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nsr.digitalbanking.dto.bankAccountDto.BankAccountDTO;
import com.nsr.digitalbanking.dto.bankAccountDto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.bankAccountDto.SavingAccountDTO;
import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.exception.CustomerNotFoundException;
import com.nsr.digitalbanking.service.BankAccountService;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {

    private BankAccountService service;

    @GetMapping("/")
    public List<BankAccountDTO> getAllBankAccounts() {
        return service.getAllBankAccounts();
    }

    @GetMapping("/{accountId}")
    public BankAccountDTO getAccount(@PathVariable String accountId) throws AccountNotFoundException {
        return service.getBankAccount(accountId);
    }

    @PostMapping("/sa")
    public SavingAccountDTO saveSavingAccount(@RequestBody SavingAccountDTO savingAccount)
            throws CustomerNotFoundException {
        return service.addSavingAccount(savingAccount);
    }

    @PostMapping("/ca")
    public CurrentAccountDTO saveCurrentAccount(@RequestBody CurrentAccountDTO accountDTO)
            throws CustomerNotFoundException {
        return service.addCurrentAccount(accountDTO);
    }

    @PutMapping("/update/sa/{id}")
    public SavingAccountDTO updateSavingAccount(@RequestBody SavingAccountDTO accountDTO, @PathVariable String id)
            throws CustomerNotFoundException {
        accountDTO.setId(id);
        return service.updateSavingAccount(accountDTO);
    }

    @PutMapping("/update/ca/{id}")
    public CurrentAccountDTO updateCurrentAccount(@RequestBody CurrentAccountDTO accountDTO, @PathVariable String id)
            throws CustomerNotFoundException {
        accountDTO.setId(id);
        return service.updateCurrentAccount(accountDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable String id) throws AccountNotFoundException {
        service.removeBankAccount(id);
        return "Account " + id + " has been deleted";
    }
}
