package com.nsr.digitalbanking.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.dto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.SavingAccountDTO;
import com.nsr.digitalbanking.exception.AccountNotFound;
import com.nsr.digitalbanking.exception.CustomerNotFound;
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

    @GetMapping("/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws AccountNotFound {
        return service.getBankAccount(accountId);
    }

    @PostMapping("/sa")
    public SavingAccountDTO saveSavingAccount(@RequestBody SavingAccountDTO savingAccount) throws CustomerNotFound {
        return service.addSavingAccount(savingAccount);
    }

    @PostMapping("/ca")
    public CurrentAccountDTO saveCurrentAccount(@RequestBody CurrentAccountDTO accountDTO) throws CustomerNotFound {
        return service.addCurrentAccount(accountDTO);
    }

    @PutMapping("/update/sa/{id}")
    public SavingAccountDTO updateSaveCurrentAccount(@RequestBody SavingAccountDTO accountDTO, @PathVariable String id)
            throws CustomerNotFound {
        accountDTO.setId(id);
        return service.updateSavingAccount(accountDTO);
    }

    @PutMapping("/update/ca/{id}")
    public CurrentAccountDTO updateCurrentAccount(@RequestBody CurrentAccountDTO accountDTO, @PathVariable String id)
            throws CustomerNotFound {
        accountDTO.setId(id);
        return service.updateCurrentAccount(accountDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable String id) throws AccountNotFound {
        service.removeBankAccount(id);
        return "Account " + id + " has been deleted";
    }
}
