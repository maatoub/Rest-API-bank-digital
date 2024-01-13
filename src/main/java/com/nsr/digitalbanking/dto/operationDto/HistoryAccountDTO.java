package com.nsr.digitalbanking.dto.operationDto;

import java.util.List;

import lombok.Data;

@Data
public class HistoryAccountDTO {
    private String RIB;
    private String name;
    private double balance;
    private List<OperationDTO> operationsDTO;
    private int sizePage;
    private int totalPage;
    private int currentPage;
}
