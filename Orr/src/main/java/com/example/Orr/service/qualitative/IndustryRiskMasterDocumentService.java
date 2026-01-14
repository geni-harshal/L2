package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AssessmentScaleEntityDto;
import com.example.Orr.dto.qualitative.FactorGroupNodeDto;
import com.example.Orr.dto.qualitative.IndustryFactorNodeDto;
import com.example.Orr.dto.qualitative.IndustryRiskMasterDocumentDto;

import java.util.List;
import java.util.Map;

public interface IndustryRiskMasterDocumentService {

    Map<String, FactorGroupNodeDto> getAllSections();
    Map<String, IndustryFactorNodeDto> getFactorsBySection(String sectionCode);
    IndustryRiskMasterDocumentDto getFullMaster();

    IndustryFactorNodeDto getFactor(
            String sectionCode,
            String factorCode
    );
    List<AssessmentScaleEntityDto> getAssessmentOptions(
            String sectionCode,
            String factorCode
    );




    IndustryRiskMasterDocumentDto createMaster(
            IndustryRiskMasterDocumentDto dto);

    IndustryRiskMasterDocumentDto updateMaster(
            IndustryRiskMasterDocumentDto dto);

    void deleteMaster();

    IndustryRiskMasterDocumentDto addOrUpdateSection(
            String sectionCode,
            FactorGroupNodeDto sectionDto);

    void deleteSection(String sectionCode);

    IndustryRiskMasterDocumentDto addOrUpdateFactor(
            String sectionCode,
            String factorCode,
            IndustryFactorNodeDto factorDto);

    void deleteFactor(
            String sectionCode,
            String factorCode);
}

