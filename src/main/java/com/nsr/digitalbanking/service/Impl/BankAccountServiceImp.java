package com.nsr.digitalbanking.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.mapper.BankAccountMapper;
import com.nsr.digitalbanking.repository.BankAccountRepository;
import com.nsr.digitalbanking.service.BankAccountService;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class BankAccountServiceImp implements BankAccountService {

    private BankAccountMapper mapper;
    private BankAccountRepository repoBankAccount;

    @Override
    public List<BankAccountDTO> getAllBankAccounts() {

        throw new UnsupportedOperationException("Unimplemented method 'getAllBankAccounts'");
    }

    @Override
    public BankAccountDTO getBankAccount(BankAccountDTO bankAccountDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBankAccount'");
    }

    @Override
    public BankAccountDTO saveBankAccount(BankAccountDTO bankAccountDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveBankAccount'");
    }

}
