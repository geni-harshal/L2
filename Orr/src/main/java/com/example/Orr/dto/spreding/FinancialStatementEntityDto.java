package com.example.Orr.dto.spreding;

import lombok.Data;

import java.util.Map;
@Data

public class FinancialStatementEntityDto {
    private String id;

    private String borrowerName;
    private String currency;
    private String borrowerClassification;
    private Map<Integer, YearlyFinancialDataEntityDto> years;
}
