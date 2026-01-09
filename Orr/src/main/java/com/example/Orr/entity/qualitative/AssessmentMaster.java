package com.example.Orr.entity.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document(collection = "assessment_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentMaster {

    @Id
    private String id;

    private Map<String, AssessmentGroup> OASA;
    private Map<String, AssessmentGroup> SOAA;
    private Map<String, AssessmentGroup> COAA;
}

