package com.example.Orr.dto.qualitative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentMasterDto {

    private Map<String, AssessmentGroupDto> OASA;
    private Map<String, AssessmentGroupDto> SOAA;
    private Map<String, AssessmentGroupDto> COAA;
}
