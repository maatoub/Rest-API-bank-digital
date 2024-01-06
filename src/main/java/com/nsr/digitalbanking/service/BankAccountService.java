package com.nsr.digitalbanking.service;

import java.util.List;

import com.nsr.digitalbanking.dto.BankAccountDTO;
import com.nsr.digitalbanking.dto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.SavingAccountDTO;
import com.nsr.digitalbanking.exception.AccountNotFound;
import com.nsr.digitalbanking.exception.CustomerNotFound;

public interface BankAccountService {
    List<BankAccountDTO> getAllBankAccounts();

    BankAccountDTO getBankAccount(String accountID) throws AccountNotFound;

    SavingAccountDTO addSavingAccount(SavingAccountDTO savingAccount) throws CustomerNotFound;

    CurrentAccountDTO addCurrentAccount(CurrentAccountDTO currentAccount) throws CustomerNotFound;

}
