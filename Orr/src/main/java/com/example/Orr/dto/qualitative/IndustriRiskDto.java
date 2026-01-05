package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.BorrowerDetails;
import lombok.Data;

@Data
public class IndustriRiskDto {
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
