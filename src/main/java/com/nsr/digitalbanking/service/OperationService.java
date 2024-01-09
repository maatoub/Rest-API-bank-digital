package com.nsr.digitalbanking.service;

import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.exception.BalanceInsufficientException;

public interface OperationService {

    void debit(double amount, String rib, String motif) throws AccountNotFoundException, BalanceInsufficientException;

    void credit(double amount, String rib, String motif) throws AccountNotFoundException;

    void transfer(double amount, String destRIB, String srcRIB, String motif) throws AccountNotFoundException, BalanceInsufficientException;
}
