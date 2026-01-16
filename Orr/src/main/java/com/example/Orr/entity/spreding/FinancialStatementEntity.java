package com.example.Orr.entity.spreding;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "financial_statements")
public class FinancialStatementEntity {

    @Id
    private String id;

    private String borrowerName;
    private String currency;
    private String borrowerClassification;
    private Map<Integer, YearlyFinancialDataEntity> years;
}
