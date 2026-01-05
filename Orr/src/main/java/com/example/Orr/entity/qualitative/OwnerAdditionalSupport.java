package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "owner_add_support")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerAdditionalSupport {
    @Id
   private String id;
    private String uUId;
    private Integer personalNetWorthScore;
}
