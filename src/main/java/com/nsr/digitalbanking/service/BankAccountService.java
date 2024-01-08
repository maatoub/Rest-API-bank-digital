package com.nsr.digitalbanking.service;

import java.util.List;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.dto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.SavingAccountDTO;
import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.exception.CustomerNotFoundException;

public interface BankAccountService {
    List<BankAccountDTO> getAllBankAccounts();

    BankAccountDTO getBankAccount(String accountID) throws AccountNotFoundException;

    SavingAccountDTO addSavingAccount(SavingAccountDTO savingAccount) throws CustomerNotFoundException;

    CurrentAccountDTO addCurrentAccount(CurrentAccountDTO currentAccount) throws CustomerNotFoundException;

    SavingAccountDTO updateSavingAccount(SavingAccountDTO savingAccount) throws CustomerNotFoundException;

    CurrentAccountDTO updateCurrentAccount(CurrentAccountDTO currentAccount) throws CustomerNotFoundException;

    void removeBankAccount(String accountID) throws AccountNotFoundException;

}
