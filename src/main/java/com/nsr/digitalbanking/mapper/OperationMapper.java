package com.nsr.digitalbanking.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.nsr.digitalbanking.dto.operationDto.OperationDTO;
import com.nsr.digitalbanking.model.Operation;

@Service
public class OperationMapper {

    public OperationDTO tOperationDTO(Operation operation) {
        OperationDTO dto = new OperationDTO();
        BeanUtils.copyProperties(operation, dto);
        return dto;
    }
}
