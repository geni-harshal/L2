package com.example.Orr.dto.spreding;

import com.example.Orr.entity.spreding.RatioCategoryEntity;
import com.example.Orr.entity.spreding.SectionNodeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data

public class FinancialStatementEntityDto {
    private String id;

    private String borrowerName;
    private String currency;
    private String borrowerClassification;

    private List<Integer> evaluationYears;

    private Map<String, SectionNodeEntityDto> balanceSheet;
    private Map<String, SectionNodeEntityDto> profitAndLoss;

    private Map<String, RatioCategoryEntityDto> financialRatios;

    private Map<Integer, Boolean> checksAndBalances;
}
