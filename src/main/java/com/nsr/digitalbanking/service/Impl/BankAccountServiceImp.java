package com.nsr.digitalbanking.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsr.digitalbanking.dto.bankAccountDto.BankAccountDTO;
import com.nsr.digitalbanking.dto.bankAccountDto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.bankAccountDto.SavingAccountDTO;
import com.nsr.digitalbanking.dto.customerDto.CustomerDTO;
import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.exception.CustomerNotFoundException;
import com.nsr.digitalbanking.mapper.BankAccountMapper;
import com.nsr.digitalbanking.mapper.CustomerMapper;
import com.nsr.digitalbanking.model.BankAccount;
import com.nsr.digitalbanking.model.CurrentAccount;
import com.nsr.digitalbanking.model.Operation;
import com.nsr.digitalbanking.model.SavingAccount;
import com.nsr.digitalbanking.repository.BankAccountRepository;
import com.nsr.digitalbanking.repository.OperationRepository;
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
    private OperationRepository repoOperation;
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
    public BankAccountDTO getBankAccount(String accountID) throws AccountNotFoundException {
        BankAccount account = repoBankAccount.findById(accountID)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account instanceof SavingAccount) {
            SavingAccount savingAccount = (SavingAccount) account;
            return mapper.toSavingAccountDto(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) account;
            return mapper.toCurrentAccountDTO(currentAccount);
        }
    }

    @Override
    public SavingAccountDTO addSavingAccount(SavingAccountDTO savingAccountDto) throws CustomerNotFoundException {
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
    public CurrentAccountDTO addCurrentAccount(CurrentAccountDTO currentAccountDto) throws CustomerNotFoundException {
        CurrentAccount currentAccount = mapper.toCurrentAccount(currentAccountDto);
        CustomerDTO customerDTO = customerService.getCustomer(currentAccountDto.getCustomerDto().getId());
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setCustomer(cusMapper.toCustomer(customerDTO));
        repoBankAccount.save(currentAccount);
        CurrentAccountDTO saved = mapper.toCurrentAccountDTO(currentAccount);
        return saved;
    }

    @Override
    public void removeBankAccount(String accountID) throws AccountNotFoundException {
        getBankAccount(accountID);
        List<Operation> operations = repoOperation.findByAccountId(accountID);
        repoOperation.deleteAll(operations);
        repoBankAccount.deleteById(accountID);
    }

    @Override
    public SavingAccountDTO updateSavingAccount(SavingAccountDTO savingAccountDto) throws CustomerNotFoundException {
        SavingAccount savingAccount = mapper.toSavingAccount(savingAccountDto);
        CustomerDTO customerDTO = customerService.getCustomer(savingAccountDto.getCustomerDto().getId());
        savingAccount.setCustomer(cusMapper.toCustomer(customerDTO));
        savingAccount.setCreatedAt(new Date());
        repoBankAccount.save(savingAccount);
        SavingAccountDTO saveDto = mapper.toSavingAccountDto(savingAccount);
        return saveDto;
    }

    @Override
    public CurrentAccountDTO updateCurrentAccount(CurrentAccountDTO currentAccountDto) throws CustomerNotFoundException {
        CurrentAccount currentAccount = mapper.toCurrentAccount(currentAccountDto);
        CustomerDTO customerDTO = customerService.getCustomer(currentAccountDto.getCustomerDto().getId());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setCustomer(cusMapper.toCustomer(customerDTO));
        repoBankAccount.save(currentAccount);
        CurrentAccountDTO saved = mapper.toCurrentAccountDTO(currentAccount);
        return saved;
    }

    
}
