package com.example.Orr.entity.qualitative;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "industry_risk_master")
@Data
public class IndustryRiskMasterDocument {

    @Id
    private String id;
    private Map<String, FactorGroupNode> factorGroups;
}
