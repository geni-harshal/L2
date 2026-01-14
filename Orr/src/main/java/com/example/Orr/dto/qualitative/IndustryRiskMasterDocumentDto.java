package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.FactorGroupNode;
import lombok.Data;

import java.util.Map;
@Data
public class IndustryRiskMasterDocumentDto {
    private String id;
    private Map<String, FactorGroupNode> factorGroups;
}
