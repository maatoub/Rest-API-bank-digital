package com.nsr.digitalbanking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nsr.digitalbanking.dto.bankAccountDto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.bankAccountDto.SavingAccountDTO;
import com.nsr.digitalbanking.model.CurrentAccount;
import com.nsr.digitalbanking.model.SavingAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BankAccountMapper {
    private CustomerMapper customerMapper;

    public SavingAccountDTO toSavingAccountDto(SavingAccount account) {
        SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
        BeanUtils.copyProperties(account, savingAccountDTO);
        savingAccountDTO.setCustomerDto(customerMapper
                .toCustomerDto(account.getCustomer()));
        savingAccountDTO.setType(account.getClass().getSimpleName());
        return savingAccountDTO;
    }

    public SavingAccount toSavingAccount(SavingAccountDTO accountDTO) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(accountDTO, savingAccount);
        savingAccount.setCustomer(customerMapper
                .toCustomer(accountDTO.getCustomerDto()));
        return savingAccount;
    }

    public CurrentAccountDTO toCurrentAccountDTO(CurrentAccount account) {
        CurrentAccountDTO accountDTO = new CurrentAccountDTO();
        BeanUtils.copyProperties(account, accountDTO);
        accountDTO.setCustomerDto(customerMapper.toCustomerDto(account.getCustomer()));
        accountDTO.setType(account.getClass().getSimpleName());
        return accountDTO;
    }

    public CurrentAccount toCurrentAccount(CurrentAccountDTO accountDto) {
        CurrentAccount account = new CurrentAccount();
        BeanUtils.copyProperties(accountDto, account);
        account.setCustomer(customerMapper.toCustomer(accountDto.getCustomerDto()));
        return account;
    }

    /************************************ */

    public SavingAccountDTO toSACustomerDTO(SavingAccount account) {
        SavingAccountDTO accountDTO = new SavingAccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setCreatedAt(account.getCreatedAt());
        accountDTO.setType(account.getClass().getSimpleName());
        return accountDTO;
    }

    public CurrentAccountDTO toCACustomerDTO(CurrentAccount account) {
        CurrentAccountDTO accountDTO = new CurrentAccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setCreatedAt(account.getCreatedAt());
        accountDTO.setType(account.getClass().getSimpleName());
        return accountDTO;
    }

}
