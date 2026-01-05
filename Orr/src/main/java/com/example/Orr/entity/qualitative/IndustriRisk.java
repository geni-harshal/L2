package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "industry_risk")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustriRisk {
    @Id
    private String id;
    private String uUId;
    private String competitiveness;
    private String environmentalConcerns;
    private String fiscalPolicyDependence;
    private String businessCyclicality;
    private String inflationSensitivity;
    private String fxSensitivity;
    private String interestRateSensitivity;
    private String industrySalesTrend;
    private String industryProfitability;
    private String industryStage;
    private String importPenetration;
    private String industryFailureRate;
    private String skilledLaborGap;
    private String productPositioning;
    private String capitalSensitivity;
    private String technologyDependence;
}
