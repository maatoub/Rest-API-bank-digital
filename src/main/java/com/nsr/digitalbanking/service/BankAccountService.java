package com.nsr.digitalbanking.service;

import java.util.List;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.dto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.SavingAccountDTO;

public interface BankAccountService {
    List<BankAccountDTO> getAllBankAccounts();

    BankAccountDTO getBankAccount(BankAccountDTO bankAccountDto);

    SavingAccountDTO addSavingAccount(SavingAccountDTO savingAccount);

    CurrentAccountDTO addCurrentAccount(CurrentAccountDTO currentAccount);

}
