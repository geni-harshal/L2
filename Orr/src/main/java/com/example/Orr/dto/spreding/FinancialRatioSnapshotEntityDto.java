package com.example.Orr.dto.spreding;

import lombok.Data;

@Data
public class FinancialRatioSnapshotEntityDto {
    private Double grossProfitMargin;
    private Double operatingProfitMargin;
    private Double netProfitMargin;

    private Double currentRatio;
    private Double quickRatio;

    private Double debtToTNW;
    private Double interestCoverageRatio;
}
