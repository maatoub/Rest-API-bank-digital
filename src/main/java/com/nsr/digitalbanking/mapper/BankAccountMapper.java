package com.nsr.digitalbanking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nsr.digitalbanking.dto.SavingAccountDTO;
import com.nsr.digitalbanking.model.SavingAccount;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankAccountMapper {
    private CustomerMapper customerMapper;

    SavingAccountDTO toSavingAccountDto(SavingAccount account) {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(account, savingAccountDTO);
        savingAccountDTO.setCustomer(customerMapper
                .toCustomerDto(account.getCustomer()));
        return savingAccountDTO;
    }
}
