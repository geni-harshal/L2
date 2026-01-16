package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.Map;

@Data
public class FinancialRatioSnapshotEntity {
    private Map<String, Map<String, Double>> ratios;

}

