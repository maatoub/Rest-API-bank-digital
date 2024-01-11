package com.nsr.digitalbanking.dto;

import com.nsr.digitalbanking.enums.AccountStatus;
import java.util.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CurrentAccountDTO extends BankAccountDTO {
    private String id;
    private Date createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private CustomerDTO customerDto;
    private double overDraft;
}
