package com.nsr.digitalbanking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nsr.digitalbanking.dto.OperationDTO;
import com.nsr.digitalbanking.exception.AccountNotFoundException;
import com.nsr.digitalbanking.service.OperationService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class OperationController {

    private OperationService service;
    
    @PostMapping("/debit")
    public String debitBalance(@RequestBody OperationDTO dto) throws AccountNotFoundException {
        service.debit( dto.getAmount(), dto.getId(), dto.getMotif());       
        return "successfully ";
    }

    @PostMapping("/credit")
    public String creditBalance(@RequestBody OperationDTO dto) throws AccountNotFoundException {
        service.debit( dto.getAmount(), dto.getId(), dto.getMotif());       
        return "successfully ";
    }
    
}
