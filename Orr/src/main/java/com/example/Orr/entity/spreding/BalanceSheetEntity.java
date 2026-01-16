package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.Map;

@Data
public class BalanceSheetEntity {

    private Map<String, Double> currentAssets;
    private Map<String, Double> nonCurrentAssets;

    private Map<String, Double> currentLiabilities;
    private Map<String, Double> nonCurrentLiabilities;

    private Map<String, Double> equity;

    private Double totalAssets;
    private Double totalLiabilities;
    private Double tangibleNetWorth;
}

