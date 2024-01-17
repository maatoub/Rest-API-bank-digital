package com.nsr.digitalbanking.dto.bankAccountDto;

import java.util.List;
import java.util.ArrayList;
import com.nsr.digitalbanking.dto.customerDto.CustomerDTO;
import lombok.Data;

@Data
public class AccountsOfCustomerDTO {
    private List<BankAccountDTO> accountsDto;
    private CustomerDTO customerDTO;

    public AccountsOfCustomerDTO() {
        accountsDto = new ArrayList<>();
    }
}