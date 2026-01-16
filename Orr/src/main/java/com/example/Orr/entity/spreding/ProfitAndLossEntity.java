package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.Map;

@Data
public class ProfitAndLossEntity {

    private Map<String, Double> revenue;
    private Map<String, Double> expenses;

    private Double grossProfit;
    private Double operatingProfit;
    private Double profitBeforeTax;
    private Double netProfit;
}

