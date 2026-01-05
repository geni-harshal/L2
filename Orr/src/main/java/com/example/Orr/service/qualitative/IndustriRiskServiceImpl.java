package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.IndustriRiskDto;
import com.example.Orr.entity.qualitative.IndustriRisk;
import com.example.Orr.service.qualitative.IndustriRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustriRiskServiceImpl implements IndustriRiskService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<IndustriRiskDto> findAll() {
        return mongoTemplate.findAll(IndustriRisk.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public IndustriRiskDto findByUUId(String uUId) {
        IndustriRisk entity = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                IndustriRisk.class
        );
        return toDto(entity);
    }

    @Override
    public IndustriRiskDto create(String uUId, IndustriRiskDto dto) {

        // prevent duplicate record for same borrower
        IndustriRisk existing = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                IndustriRisk.class
        );

        if (existing != null) {
            throw new RuntimeException(
                    "IndustriRisk already exists for UUID: " + uUId
            );
        }

        IndustriRisk entity = new IndustriRisk();
        entity.setUUId(uUId);
        entity.setCompetitiveness(dto.getCompetitiveness());
        entity.setEnvironmentalConcerns(dto.getEnvironmentalConcerns());
        entity.setFiscalPolicyDependence(dto.getFiscalPolicyDependence());
        entity.setBusinessCyclicality(dto.getBusinessCyclicality());
        entity.setInflationSensitivity(dto.getInflationSensitivity());
        entity.setFxSensitivity(dto.getFxSensitivity());
        entity.setInterestRateSensitivity(dto.getInterestRateSensitivity());
        entity.setIndustrySalesTrend(dto.getIndustrySalesTrend());
        entity.setIndustryProfitability(dto.getIndustryProfitability());
        entity.setIndustryStage(dto.getIndustryStage());
        entity.setImportPenetration(dto.getImportPenetration());
        entity.setIndustryFailureRate(dto.getIndustryFailureRate());
        entity.setSkilledLaborGap(dto.getSkilledLaborGap());
        entity.setProductPositioning(dto.getProductPositioning());
        entity.setCapitalSensitivity(dto.getCapitalSensitivity());
        entity.setTechnologyDependence(dto.getTechnologyDependence());

        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public IndustriRiskDto updateByUUId(String uUId, IndustriRiskDto dto) {

        Query query = Query.query(Criteria.where("uUId").is(uUId));

        Update update = new Update()
                .set("competitiveness", dto.getCompetitiveness())
                .set("environmentalConcerns", dto.getEnvironmentalConcerns())
                .set("fiscalPolicyDependence", dto.getFiscalPolicyDependence())
                .set("businessCyclicality", dto.getBusinessCyclicality())
                .set("inflationSensitivity", dto.getInflationSensitivity())
                .set("fxSensitivity", dto.getFxSensitivity())
                .set("interestRateSensitivity", dto.getInterestRateSensitivity())
                .set("industrySalesTrend", dto.getIndustrySalesTrend())
                .set("industryProfitability", dto.getIndustryProfitability())
                .set("industryStage", dto.getIndustryStage())
                .set("importPenetration", dto.getImportPenetration())
                .set("industryFailureRate", dto.getIndustryFailureRate())
                .set("skilledLaborGap", dto.getSkilledLaborGap())
                .set("productPositioning", dto.getProductPositioning())
                .set("capitalSensitivity", dto.getCapitalSensitivity())
                .set("technologyDependence", dto.getTechnologyDependence());

        mongoTemplate.updateFirst(query, update, IndustriRisk.class);

        return findByUUId(uUId);
    }

    @Override
    public void deleteByUUId(String uUId) {
        mongoTemplate.remove(
                Query.query(Criteria.where("uUId").is(uUId)),
                IndustriRisk.class
        );
    }

    private IndustriRiskDto toDto(IndustriRisk entity) {
        if (entity == null) return null;

        IndustriRiskDto dto = new IndustriRiskDto();
        dto.setUUId(entity.getUUId());
        dto.setCompetitiveness(entity.getCompetitiveness());
        dto.setEnvironmentalConcerns(entity.getEnvironmentalConcerns());
        dto.setFiscalPolicyDependence(entity.getFiscalPolicyDependence());
        dto.setBusinessCyclicality(entity.getBusinessCyclicality());
        dto.setInflationSensitivity(entity.getInflationSensitivity());
        dto.setFxSensitivity(entity.getFxSensitivity());
        dto.setInterestRateSensitivity(entity.getInterestRateSensitivity());
        dto.setIndustrySalesTrend(entity.getIndustrySalesTrend());
        dto.setIndustryProfitability(entity.getIndustryProfitability());
        dto.setIndustryStage(entity.getIndustryStage());
        dto.setImportPenetration(entity.getImportPenetration());
        dto.setIndustryFailureRate(entity.getIndustryFailureRate());
        dto.setSkilledLaborGap(entity.getSkilledLaborGap());
        dto.setProductPositioning(entity.getProductPositioning());
        dto.setCapitalSensitivity(entity.getCapitalSensitivity());
        dto.setTechnologyDependence(entity.getTechnologyDependence());
        return dto;
    }
}
