package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.BorrowerDetails;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class OwnerAdditionalSupportDto {
    private String id;
    private String uUId;
    private Integer personalNetWorthScore;

}
