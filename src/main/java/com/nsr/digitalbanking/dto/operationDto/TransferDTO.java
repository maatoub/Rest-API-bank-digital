package com.nsr.digitalbanking.dto.operationDto;

import lombok.Data;

@Data
public class TransferDTO {
    private String ribDest;
    private String ribSrc;
    private String motif;
    private double amount;
}