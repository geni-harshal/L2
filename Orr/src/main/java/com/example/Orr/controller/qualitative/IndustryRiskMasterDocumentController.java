package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.*;
import com.example.Orr.service.qualitative.IndustryRiskMasterDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/industry-risk/master")
public class IndustryRiskMasterDocumentController {

    private final IndustryRiskMasterDocumentService service;

    public IndustryRiskMasterDocumentController(
            IndustryRiskMasterDocumentService service) {
        this.service = service;
    }


    @PostMapping("create")
    public IndustryRiskMasterDocumentDto createMaster(
            @RequestBody IndustryRiskMasterDocumentDto dto) {
        return service.createMaster(dto);
    }

    @PutMapping("/update")
    public IndustryRiskMasterDocumentDto updateMaster(
            @RequestBody IndustryRiskMasterDocumentDto dto) {
        return service.updateMaster(dto);
    }

    @DeleteMapping("/delete")
    public void deleteMaster() {
        service.deleteMaster();
    }

    @GetMapping("/full")
    public ResponseEntity<IndustryRiskMasterDocumentDto> getFullMaster() {
        return ResponseEntity.ok(service.getFullMaster());
    }


    @GetMapping("/sections")
    public Map<String, FactorGroupNodeDto> getAllSections() {
        return service.getAllSections();
    }

    @PutMapping("/update/sections/{sectionCode}")
    public IndustryRiskMasterDocumentDto addOrUpdateSection(
            @PathVariable String sectionCode,
            @RequestBody FactorGroupNodeDto dto) {
        return service.addOrUpdateSection(sectionCode, dto);
    }

    @DeleteMapping("/delete/sections/{sectionCode}")
    public void deleteSection(
            @PathVariable String sectionCode) {
        service.deleteSection(sectionCode);
    }


    @GetMapping("/sections/{sectionCode}/factors")
    public Map<String, IndustryFactorNodeDto> getFactorsBySection(
            @PathVariable String sectionCode) {
        return service.getFactorsBySection(sectionCode);
    }

    @GetMapping("/sections/{sectionCode}/factors/{factorCode}")
    public IndustryFactorNodeDto getFactor(
            @PathVariable String sectionCode,
            @PathVariable String factorCode) {
        return service.getFactor(sectionCode, factorCode);
    }

    @PutMapping("/update/sections/{sectionCode}/factors/{factorCode}")
    public IndustryRiskMasterDocumentDto addOrUpdateFactor(
            @PathVariable String sectionCode,
            @PathVariable String factorCode,
            @RequestBody IndustryFactorNodeDto dto) {
        return service.addOrUpdateFactor(sectionCode, factorCode, dto);
    }

    @DeleteMapping("/delete/sections/{sectionCode}/factors/{factorCode}")
    public void deleteFactor(
            @PathVariable String sectionCode,
            @PathVariable String factorCode) {
        service.deleteFactor(sectionCode, factorCode);
    }


    @GetMapping("/sections/{sectionCode}/factors/{factorCode}/assessments")
    public List<AssessmentScaleEntityDto> getAssessmentOptions(
            @PathVariable String sectionCode,
            @PathVariable String factorCode) {
        return service.getAssessmentOptions(sectionCode, factorCode);
    }
}
