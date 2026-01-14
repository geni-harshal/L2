package com.example.Orr.dto.qualitative;

import com.example.Orr.entity.qualitative.AssessmentScaleEntity;
import lombok.Data;

import java.util.List;
@Data
public class IndustryFactorNodeDto {
    private String name;
    private String clarity;
    private List<AssessmentScaleEntity> assessmentOptions;
}
