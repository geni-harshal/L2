package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.BorrowerDetails;
import lombok.Data;

@Data
public class AccountStatusDto {

    private String id;
    private String uUId;
    private Integer yearInBusiness;
    private Integer locationOfBusiness;
    private Integer relationshipAge;
    private Integer auditorQuality;
    private Integer auditorOpinion;
    private Integer nationalizationScheme;
}
