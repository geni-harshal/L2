package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.*;
import com.example.Orr.entity.qualitative.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndustryRiskMasterDocumentServiceImpl
        implements IndustryRiskMasterDocumentService {

    private static final String MASTER_ID = "INDUSTRY_RISK_MASTER";

    private final MongoTemplate mongoTemplate;

    public IndustryRiskMasterDocumentServiceImpl(
            MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public IndustryRiskMasterDocumentDto createMaster(
            IndustryRiskMasterDocumentDto dto) {

        if (mongoTemplate.exists(
                Query.query(Criteria.where("_id").is(MASTER_ID)),
                IndustryRiskMasterDocument.class)) {
            throw new IllegalStateException(
                    "Industry Risk Master already exists");
        }

        IndustryRiskMasterDocument entity = toEntity(dto);
        entity.setId(MASTER_ID);

        mongoTemplate.insert(entity);
        return toDto(entity);
    }


    @Override
    public IndustryRiskMasterDocumentDto getFullMaster() {

        IndustryRiskMasterDocument master = fetchMaster();
        return toDto(master);
    }


    @Override
    public IndustryRiskMasterDocumentDto updateMaster(
            IndustryRiskMasterDocumentDto dto) {

        IndustryRiskMasterDocument entity = toEntity(dto);
        entity.setId(MASTER_ID);

        IndustryRiskMasterDocument saved =
                mongoTemplate.save(entity);

        return toDto(saved);
    }


    @Override
    public void deleteMaster() {

        mongoTemplate.remove(
                Query.query(Criteria.where("_id").is(MASTER_ID)),
                IndustryRiskMasterDocument.class);
    }


    @Override
    public IndustryRiskMasterDocumentDto addOrUpdateSection(
            String sectionCode,
            FactorGroupNodeDto dto) {

        IndustryRiskMasterDocument master = fetchMaster();

        master.getFactorGroups()
                .put(sectionCode, toSectionEntity(dto));

        mongoTemplate.save(master);
        return toDto(master);
    }


    @Override
    public void deleteSection(String sectionCode) {

        IndustryRiskMasterDocument master = fetchMaster();

        if (master.getFactorGroups().remove(sectionCode) == null) {
            throw new IllegalArgumentException(
                    "Invalid section code: " + sectionCode);
        }

        mongoTemplate.save(master);
    }


    @Override
    public IndustryRiskMasterDocumentDto addOrUpdateFactor(
            String sectionCode,
            String factorCode,
            IndustryFactorNodeDto dto) {

        IndustryRiskMasterDocument master = fetchMaster();

        FactorGroupNode section =
                getSection(master, sectionCode);

        section.getFactors()
                .put(factorCode, toFactorEntity(dto));

        mongoTemplate.save(master);
        return toDto(master);
    }


    @Override
    public void deleteFactor(
            String sectionCode,
            String factorCode) {

        IndustryRiskMasterDocument master = fetchMaster();

        FactorGroupNode section =
                getSection(master, sectionCode);

        if (section.getFactors().remove(factorCode) == null) {
            throw new IllegalArgumentException(
                    "Invalid factor code: " + factorCode);
        }

        mongoTemplate.save(master);
    }


    @Override
    public Map<String, FactorGroupNodeDto> getAllSections() {

        IndustryRiskMasterDocument master = fetchMaster();
        Map<String, FactorGroupNodeDto> response =
                new LinkedHashMap<>();

        master.getFactorGroups()
                .forEach((code, entity) ->
                        response.put(code, toSectionDto(entity)));

        return response;
    }


    @Override
    public Map<String, IndustryFactorNodeDto> getFactorsBySection(
            String sectionCode) {

        FactorGroupNode section =
                getSection(fetchMaster(), sectionCode);

        Map<String, IndustryFactorNodeDto> response =
                new LinkedHashMap<>();

        section.getFactors()
                .forEach((code, entity) ->
                        response.put(code, toFactorSummaryDto(entity)));

        return response;
    }


    @Override
    public IndustryFactorNodeDto getFactor(
            String sectionCode,
            String factorCode) {

        IndustryFactorNode factor =
                getFactorEntity(fetchMaster(),
                        sectionCode, factorCode);

        return toFactorDto(factor);
    }


    @Override
    public List<AssessmentScaleEntityDto> getAssessmentOptions(
            String sectionCode,
            String factorCode) {

        IndustryFactorNode factor =
                getFactorEntity(fetchMaster(),
                        sectionCode, factorCode);

        return factor.getAssessmentOptions()
                .stream()
                .map(this::toAssessmentDto)
                .toList();
    }


    private IndustryRiskMasterDocument fetchMaster() {

        IndustryRiskMasterDocument doc =
                mongoTemplate.findById(
                        MASTER_ID,
                        IndustryRiskMasterDocument.class);

        if (doc == null) {
            throw new IllegalStateException(
                    "Industry Risk Master not found");
        }
        return doc;
    }

    private FactorGroupNode getSection(
            IndustryRiskMasterDocument master,
            String sectionCode) {

        FactorGroupNode section =
                master.getFactorGroups().get(sectionCode);

        if (section == null) {
            throw new IllegalArgumentException(
                    "Invalid section code: " + sectionCode);
        }
        return section;
    }

    private IndustryFactorNode getFactorEntity(
            IndustryRiskMasterDocument master,
            String sectionCode,
            String factorCode) {

        FactorGroupNode section =
                getSection(master, sectionCode);

        IndustryFactorNode factor =
                section.getFactors().get(factorCode);

        if (factor == null) {
            throw new IllegalArgumentException(
                    "Invalid factor code: " + factorCode);
        }
        return factor;
    }


    private IndustryRiskMasterDocument toEntity(
            IndustryRiskMasterDocumentDto dto) {

        IndustryRiskMasterDocument entity =
                new IndustryRiskMasterDocument();

        entity.setFactorGroups(
                dto.getFactorGroups() == null
                        ? new LinkedHashMap<>()
                        : dto.getFactorGroups()
        );
        return entity;
    }

    private IndustryRiskMasterDocumentDto toDto(
            IndustryRiskMasterDocument entity) {

        IndustryRiskMasterDocumentDto dto =
                new IndustryRiskMasterDocumentDto();

        dto.setId(entity.getId());
        dto.setFactorGroups(entity.getFactorGroups());
        return dto;
    }

    private FactorGroupNode toSectionEntity(
            FactorGroupNodeDto dto) {

        FactorGroupNode entity = new FactorGroupNode();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setFactors(
                dto.getFactors() == null
                        ? new LinkedHashMap<>()
                        : dto.getFactors()
        );
        return entity;
    }

    private FactorGroupNodeDto toSectionDto(
            FactorGroupNode entity) {

        FactorGroupNodeDto dto =
                new FactorGroupNodeDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private IndustryFactorNode toFactorEntity(
            IndustryFactorNodeDto dto) {

        IndustryFactorNode entity =
                new IndustryFactorNode();
        entity.setName(dto.getName());
        entity.setClarity(dto.getClarity());
        entity.setAssessmentOptions(dto.getAssessmentOptions());
        return entity;
    }

    private IndustryFactorNodeDto toFactorSummaryDto(
            IndustryFactorNode entity) {

        IndustryFactorNodeDto dto =
                new IndustryFactorNodeDto();
        dto.setName(entity.getName());
        dto.setClarity(entity.getClarity());
        return dto;
    }

    private IndustryFactorNodeDto toFactorDto(
            IndustryFactorNode entity) {

        IndustryFactorNodeDto dto =
                new IndustryFactorNodeDto();
        dto.setName(entity.getName());
        dto.setClarity(entity.getClarity());
        dto.setAssessmentOptions(entity.getAssessmentOptions());
        return dto;
    }

    private AssessmentScaleEntityDto toAssessmentDto(
            AssessmentScaleEntity entity) {

        AssessmentScaleEntityDto dto =
                new AssessmentScaleEntityDto();
        dto.setScore(entity.getScore());
        dto.setLabel(entity.getLabel());
        dto.setMeaning(entity.getMeaning());
        return dto;
    }
}
