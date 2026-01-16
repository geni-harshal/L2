package com.example.Orr.service.spreding;

import com.example.Orr.dto.spreding.FinancialStatementEntityDto;
import com.example.Orr.dto.spreding.YearlyFinancialDataEntityDto;


import java.util.List;
import java.util.Map;

public interface FinancialStatementService {

    String create(FinancialStatementEntityDto dto);

    FinancialStatementEntityDto getById(String id);

    List<FinancialStatementEntityDto> getAll();

    FinancialStatementEntityDto update(String id, FinancialStatementEntityDto dto);

    void delete(String id);

    // YEAR LEVEL CRUD
    FinancialStatementEntityDto upsertYear(
            String id,
            Integer year,
            YearlyFinancialDataEntityDto yearDto
    );

    YearlyFinancialDataEntityDto getYear(
            String id,
            Integer year
    );

    void deleteYear(
            String id,
            Integer year
    );

    String createStatement(
            String borrowerName,
            String currency,
            String borrowerClassification
    );


    Map<Integer, YearlyFinancialDataEntityDto> getAllYears(
            String statementId
    );


    void createYear(
            String statementId,
            Integer year,
            YearlyFinancialDataEntityDto dto
    );

}
