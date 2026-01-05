package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatus {
    @Id
   private  String id;
    private String uUId;
    private Integer yearInBusiness;
    private Integer locationOfBusiness;
    private Integer relationshipAge;
    private Integer auditorQuality;
    private Integer auditorOpinion;
    private Integer nationalizationScheme;
}
