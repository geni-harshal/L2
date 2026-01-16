package com.example.Orr.dto.spreding;

import com.example.Orr.entity.spreding.BalanceSheetEntity;
import com.example.Orr.entity.spreding.FinancialRatioSnapshotEntity;
import com.example.Orr.entity.spreding.ProfitAndLossEntity;
import lombok.Data;
import java.util.Map;
@Data
public class YearlyFinancialDataEntityDto {
    private BalanceSheetEntity balanceSheet;
    private ProfitAndLossEntity profitAndLoss;
    private FinancialRatioSnapshotEntity financialRatios;

    private Boolean checksAndBalances;
}
