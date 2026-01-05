package com.example.Orr.entity.qualitative;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "borrower_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDetails {
    @Id
    private String id;
    private String uUId;
    private Integer borrowerId;
    private String name;
    private String currencyCode;
    private String classificationCode;
    private LocalDate assessmentDate;
    private String relationshipManager;
    private String industryCode;

}
