package com.nsr.digitalbanking.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.dto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.CustomerDTO;
import com.nsr.digitalbanking.dto.SavingAccountDTO;
import com.nsr.digitalbanking.exception.AccountNotFound;
import com.nsr.digitalbanking.exception.CustomerNotFound;
import com.nsr.digitalbanking.mapper.BankAccountMapper;
import com.nsr.digitalbanking.mapper.CustomerMapper;
import com.nsr.digitalbanking.model.BankAccount;
import com.nsr.digitalbanking.model.CurrentAccount;
import com.nsr.digitalbanking.model.SavingAccount;
import com.nsr.digitalbanking.repository.BankAccountRepository;
import com.nsr.digitalbanking.service.BankAccountService;
import com.nsr.digitalbanking.service.CustomerService;

import lombok.AllArgsConstructor;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class BankAccountServiceImp implements BankAccountService {

    private BankAccountMapper mapper;
    private BankAccountRepository repoBankAccount;
    private CustomerService customerService;
    private CustomerMapper cusMapper;

    @Override
    public List<BankAccountDTO> getAllBankAccounts() {
        List<BankAccountDTO> accountDTOs = repoBankAccount.findAll().stream().map(acc -> {
            if (acc instanceof SavingAccount) {
                SavingAccount bankAccount = (SavingAccount) acc;
                return mapper.toSavingAccountDto(bankAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) acc;
                return mapper.toCurrentAccountDTO(currentAccount);
            }
        }).collect(Collectors.toList());
        return accountDTOs;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountID) throws AccountNotFound {
        BankAccount account = repoBankAccount.findById(accountID)
                .orElseThrow(() -> new AccountNotFound("Account not found"));
        if (account instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) account;
            return mapper.toSavingAccountDto(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) account;
            return mapper.toCurrentAccountDTO(currentAccount);
        }
    }

    @Override
    public SavingAccountDTO addSavingAccount(SavingAccountDTO savingAccountDto) throws CustomerNotFound {
        if (savingAccountDto == null)
            new AccountNotFound("Account not found");
        SavingAccount savingAccount = mapper.toSavingAccount(savingAccountDto);
        CustomerDTO customerDTO = customerService.getCustomer(savingAccountDto.getCustomerDto().getId());
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCustomer(cusMapper.toCustomer(customerDTO));
        savingAccount.setCreatedAt(new Date());
        repoBankAccount.save(savingAccount);
        SavingAccountDTO saveDto = mapper.toSavingAccountDto(savingAccount);
        return saveDto;
    }

    @Override
    public CurrentAccountDTO addCurrentAccount(CurrentAccountDTO currentAccountDto) throws CustomerNotFound {
        CurrentAccount currentAccount = mapper.toCurrentAccount(currentAccountDto);
        CustomerDTO customerDTO = customerService.getCustomer(currentAccountDto.getCustomerDto().getId());
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setCustomer(cusMapper.toCustomer(customerDTO));
        repoBankAccount.save(currentAccount);
        CurrentAccountDTO saved = mapper.toCurrentAccountDTO(currentAccount);
        return saved;
    }

}
