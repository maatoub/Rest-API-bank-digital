package com.nsr.digitalbanking.dto;

import com.nsr.digitalbanking.enums.OperationType;

import java.util.Date;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class OperationDTO {
    private Long id;
    private Date opDate;
    private double amount;
    private String motif;
    @Enumerated(EnumType.STRING)
    private OperationType type;
}
