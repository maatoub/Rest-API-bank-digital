package com.nsr.digitalbanking.service;

import java.util.List;

import com.nsr.digitalbanking.dto.BankAccountDTO;

public interface BankAccountService {
    List<BankAccountDTO> getAllBankAccounts();

    BankAccountDTO getBankAccount(BankAccountDTO bankAccountDto);

    BankAccountDTO saveBankAccount(BankAccountDTO bankAccountDto);
    
    
}
