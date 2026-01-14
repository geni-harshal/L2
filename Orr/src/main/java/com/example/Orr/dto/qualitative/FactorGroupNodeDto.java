package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.IndustryFactorNode;
import lombok.Data;

import java.util.Map;
@Data
public class FactorGroupNodeDto {
    private String name;     // Structural Factors
    private String description;
    private Map<String, IndustryFactorNode> factors;
}
