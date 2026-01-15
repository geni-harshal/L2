package com.example.Orr.service.spreding;

import com.example.Orr.dto.spreding.FinancialStatementEntityDto;

import java.util.List;

public interface FinancialStatementService {

    FinancialStatementEntityDto create(
            FinancialStatementEntityDto dto);

    FinancialStatementEntityDto update(
            String id,
            FinancialStatementEntityDto dto);

    FinancialStatementEntityDto getById(
            String id);

    List<FinancialStatementEntityDto> getAll();

    void delete(String id);
}
