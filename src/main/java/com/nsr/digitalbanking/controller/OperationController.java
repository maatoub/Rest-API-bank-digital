package com.nsr.digitalbanking.controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.nsr.digitalbanking.dto.operationDto.OperationDTO;
import com.nsr.digitalbanking.dto.operationDto.OperationRequest;
import com.nsr.digitalbanking.dto.operationDto.TransferDTO;
import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.exception.BalanceInsufficientException;
import com.nsr.digitalbanking.service.OperationService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class OperationController {

    private OperationService service;

    @PostMapping("/debit")
    public OperationRequest debitBalance(@RequestBody OperationRequest request)
            throws AccountNotFoundException,
            BalanceInsufficientException {
        service.debit(request.getAmount(), request.getRib(), request.getMotif());
        return request;
    }

    @PostMapping("/credit")
    public OperationRequest creditBalance(@RequestBody OperationRequest request) throws AccountNotFoundException {
        service.credit(request.getAmount(), request.getRib(), request.getMotif());
        return request;
    }

    @PostMapping("/transfer")
    public TransferDTO transferAmount(@RequestBody TransferDTO dto)
            throws AccountNotFoundException,
            BalanceInsufficientException {
        service.transfer(dto.getAmount(), dto.getRibDest(), dto.getRibSrc(), dto.getMotif());
        return dto;
    }

    @GetMapping("/history/{accountID}")
    public List<OperationDTO> getHistoryAccount(@PathVariable String accountID) {
        return service.accountHistory(accountID);
    }
}
