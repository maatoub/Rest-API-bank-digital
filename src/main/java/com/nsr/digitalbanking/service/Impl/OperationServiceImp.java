package com.nsr.digitalbanking.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsr.digitalbanking.dto.operationDto.OperationDTO;
import com.nsr.digitalbanking.enums.OperationType;
import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.exception.BalanceInsufficientException;
import com.nsr.digitalbanking.mapper.OperationMapper;
import com.nsr.digitalbanking.model.BankAccount;
import com.nsr.digitalbanking.model.Operation;
import com.nsr.digitalbanking.repository.BankAccountRepository;
import com.nsr.digitalbanking.repository.OperationRepository;
import com.nsr.digitalbanking.service.OperationService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OperationServiceImp implements OperationService {

    private OperationRepository repoOperation;
    private BankAccountRepository repoAccount;
    private OperationMapper mapper;

    @Override
    public void debit(double amount, String rib, String motif)
            throws AccountNotFoundException, BalanceInsufficientException {
        BankAccount account = repoAccount.findById(rib)
                .orElseThrow(() -> new AccountNotFoundException("account not found"));
        if (amount > account.getBalance())
            throw new BalanceInsufficientException("Balance insufficient");
        double amountUpdate = account.getBalance() - amount;
        Operation op = new Operation();
        op.setMotif(motif);
        op.setAmount(amount);
        op.setOpDate(new Date());
        op.setType(OperationType.DEBIT);
        op.setAccount(account);
        repoOperation.save(op);
        account.setBalance(amountUpdate);
        mapper.tOperationDTO(op);
        repoAccount.save(account);
        
    }

    @Override
    public void credit(double amount, String rib, String motif) throws AccountNotFoundException {
        BankAccount account = repoAccount.findById(rib)
                .orElseThrow(() -> new AccountNotFoundException("account not found"));
        double amountUpdate = account.getBalance() + amount;
        Operation op = new Operation();
        op.setMotif(motif);
        op.setAmount(amount);
        op.setOpDate(new Date());
        op.setType(OperationType.CREDIT);
        op.setAccount(account);
        repoOperation.save(op);
        account.setBalance(amountUpdate);
        repoAccount.save(account);
    }

    @Override
    public void transfer(double amount, String destRIB, String srcRIB, String motif)
            throws AccountNotFoundException, BalanceInsufficientException {
        debit(amount, srcRIB, motif);
        credit(amount, destRIB, motif);
    }

    @Override
    public List<OperationDTO> accountHistory(String accountID) {
        List<OperationDTO> accounts = repoOperation.findByAccountId(accountID).stream()
                .map(op -> mapper.tOperationDTO(op)).collect(Collectors.toList());
        return accounts;
    }

}
