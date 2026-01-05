package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.BorrowerDetails;
import lombok.Data;

@Data
public class AccountConductDto {
    private String id;
    private String uUId;
    private Integer bounceCheques;
    private Integer ongoingCreditRelationship;
    private Integer delayInInstallments;
    private Integer delinquencyHistory;
    private Integer writeOff;
    private Integer fraudLitigation;
}
