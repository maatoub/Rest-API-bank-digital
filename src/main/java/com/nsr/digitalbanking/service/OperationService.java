package com.nsr.digitalbanking.service;

import com.nsr.digitalbanking.exception.AccountNotFoundException;

public interface OperationService {

    void debit(double amount, String rib, String motif) throws AccountNotFoundException;

    void credit(double amount, String rib, String motif) throws AccountNotFoundException;

    void transfer(double amount, String destRIB, String srcRIB);
}
