package com.example.Orr.dto.qualitative;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerDetailsDto {
    private String id;
    private String uUId;
    private Integer borrowerId;
    private String name;
    private String currencyCode;
    private String currencyName;
    private String currencySymbol;
    private String classificationCode;
    private String classificationName;
    private Integer riskWeight;
    private LocalDate assessmentDate;
    private String relationshipManager;
    private String industryCode;
    private String industryName;
}
