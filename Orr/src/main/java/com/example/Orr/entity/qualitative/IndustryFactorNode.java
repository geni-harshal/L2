package com.example.Orr.entity.qualitative;

import lombok.Data;

import java.util.List;

@Data
public class IndustryFactorNode{

    private String name;
    private String clarity;
    private List<AssessmentScaleEntity> assessmentOptions;
}
