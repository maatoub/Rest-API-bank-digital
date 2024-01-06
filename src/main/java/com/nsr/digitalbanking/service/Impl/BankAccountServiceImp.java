package com.nsr.digitalbanking.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.dto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.SavingAccountDTO;
import com.nsr.digitalbanking.mapper.BankAccountMapper;
import com.nsr.digitalbanking.model.BankAccount;
import com.nsr.digitalbanking.model.CurrentAccount;
import com.nsr.digitalbanking.model.SavingAccount;
import com.nsr.digitalbanking.repository.BankAccountRepository;
import com.nsr.digitalbanking.service.BankAccountService;

import lombok.AllArgsConstructor;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class BankAccountServiceImp implements BankAccountService {

    private BankAccountMapper mapper;
    private BankAccountRepository repoBankAccount;

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
    public BankAccountDTO getBankAccount(BankAccountDTO bankAccountDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBankAccount'");
    }

    @Override
    public SavingAccountDTO addSavingAccount(SavingAccountDTO savingAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSavingAccount'");
    }

    @Override
    public CurrentAccountDTO addCurrentAccount(CurrentAccountDTO currentAccount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCurrentAccount'");
    }

}
