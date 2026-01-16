package com.example.Orr.dto.spreding;

import lombok.Data;

import java.util.Map;
@Data
public class BalanceSheetEntityDto {
    private Map<String, Double> currentAssets;
    private Map<String, Double> nonCurrentAssets;

    private Map<String, Double> currentLiabilities;
    private Map<String, Double> nonCurrentLiabilities;

    private Map<String, Double> equity;

    private Double totalAssets;
    private Double totalLiabilities;
    private Double tangibleNetWorth;
}
