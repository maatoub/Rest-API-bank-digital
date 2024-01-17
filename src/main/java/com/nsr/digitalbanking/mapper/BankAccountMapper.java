package com.nsr.digitalbanking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nsr.digitalbanking.dto.bankAccountDto.CurrentAccountDTO;
import com.nsr.digitalbanking.dto.bankAccountDto.CustomerAccDetailsDTO;
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

    public CustomerAccDetailsDTO toCustomerSavingAccountDto(SavingAccount account) {
        CustomerAccDetailsDTO detailsDTO = new CustomerAccDetailsDTO();
        detailsDTO.setDate(account.getCreatedAt());
        detailsDTO.setRIB(account.getId());
        detailsDTO.setStatus(account.getStatus());
        detailsDTO.setType(account.getClass().getSimpleName());
        detailsDTO.setCustomerDTO(customerMapper.toCustomerDto(account.getCustomer()));
        return detailsDTO;
    }

    public CustomerAccDetailsDTO toCustomerCurrentAccountDto(CurrentAccount account) {
        CustomerAccDetailsDTO detailsDTO = new CustomerAccDetailsDTO();
        detailsDTO.setDate(account.getCreatedAt());
        detailsDTO.setRIB(account.getId());
        detailsDTO.setStatus(account.getStatus());
        detailsDTO.setType(account.getClass().getSimpleName());
        detailsDTO.setCustomerDTO(customerMapper.toCustomerDto(account.getCustomer()));
        return detailsDTO;
    }

}
