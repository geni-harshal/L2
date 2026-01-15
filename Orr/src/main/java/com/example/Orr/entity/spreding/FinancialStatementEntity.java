package com.example.Orr.entity.spreding;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "financial_statement")
@Data
public class FinancialStatementEntity {

    @Id
    private String id;

    private String borrowerName;
    private String currency;
    private String borrowerClassification;

    private List<Integer> evaluationYears;

    private Map<String, SectionNodeEntity> balanceSheet;
    private Map<String, SectionNodeEntity> profitAndLoss;

    private Map<String, RatioCategoryEntity> financialRatios;

    private Map<Integer, Boolean> checksAndBalances;


}
