package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_conduct")

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountConduct {
    @Id
    private String id;
    private String uUId;
    private Integer bounceCheques;
    private Integer ongoingCreditRelationship;
    private Integer delayInInstallments;
    private Integer delinquencyHistory;
    private Integer writeOff;
    private Integer fraudLitigation;
}
