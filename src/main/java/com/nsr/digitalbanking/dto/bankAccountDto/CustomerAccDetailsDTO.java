package com.nsr.digitalbanking.dto.bankAccountDto;

import java.util.Date;

import com.nsr.digitalbanking.dto.customerDto.CustomerDTO;
import com.nsr.digitalbanking.enums.AccountStatus;

import lombok.Data;

@Data
public class CustomerAccDetailsDTO {
    private String RIB;
    private AccountStatus status;
    private String type;
    private Date date;
    private CustomerDTO customerDTO;
}
