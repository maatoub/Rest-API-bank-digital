package com.nsr.digitalbanking.dto;

import lombok.Data;

@Data
public class OperationRequest {
    private String rib;
    private double amount;
    private String motif;
}
