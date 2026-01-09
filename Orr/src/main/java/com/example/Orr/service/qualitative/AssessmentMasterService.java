package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AssessmentGroupDto;
import com.example.Orr.dto.qualitative.AssessmentMasterDto;

public interface AssessmentMasterService {

    AssessmentMasterDto getAssessmentMaster();
    AssessmentMasterDto getOASA();
    AssessmentMasterDto getSOAA();
    AssessmentMasterDto getCOAA();


    void addSubGroup(String section, String key, AssessmentGroupDto dto);

    void updateSubGroup(String section, String key, AssessmentGroupDto dto);

    void deleteSubGroup(String section, String key);
}

