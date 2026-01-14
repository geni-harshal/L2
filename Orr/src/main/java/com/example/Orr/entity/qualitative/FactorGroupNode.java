package com.example.Orr.entity.qualitative;

import lombok.Data;

import java.util.Map;

@Data
public class FactorGroupNode {

    private String name;     // Structural Factors
    private String description;
    private Map<String, IndustryFactorNode> factors;
}
