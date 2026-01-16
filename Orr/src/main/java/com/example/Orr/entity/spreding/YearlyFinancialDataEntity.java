package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.Map;

@Data
public class YearlyFinancialDataEntity {
    private BalanceSheetEntity balanceSheet;
    private ProfitAndLossEntity profitAndLoss;
    private FinancialRatioSnapshotEntity financialRatios;

    private Boolean checksAndBalances;
}
