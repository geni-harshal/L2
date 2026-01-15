package com.example.Orr.service.spreding;

import com.example.Orr.dto.spreding.FinancialStatementEntityDto;
import com.example.Orr.dto.spreding.RatioCategoryEntityDto;
import com.example.Orr.dto.spreding.RatioEntityDto;
import com.example.Orr.dto.spreding.SectionNodeEntityDto;
import com.example.Orr.entity.spreding.FinancialStatementEntity;
import com.example.Orr.entity.spreding.RatioCategoryEntity;
import com.example.Orr.entity.spreding.RatioEntity;
import com.example.Orr.entity.spreding.SectionNodeEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialStatementServiceImpl
        implements FinancialStatementService {

    private final MongoTemplate mongoTemplate;

    public FinancialStatementServiceImpl(
            MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }



    @Override
    public FinancialStatementEntityDto create(
            FinancialStatementEntityDto dto) {

        FinancialStatementEntity entity = toEntity(dto);
        entity.setId(null);

        FinancialStatementEntity saved =
                mongoTemplate.save(entity);

        return toDto(saved);
    }


    @Override
    public FinancialStatementEntityDto update(
            String id,
            FinancialStatementEntityDto dto) {

        Query query = Query.query(
                Criteria.where("_id").is(id));

        FinancialStatementEntity existing =
                mongoTemplate.findOne(
                        query,
                        FinancialStatementEntity.class);

        if (existing == null) {
            throw new IllegalArgumentException(
                    "FinancialStatement not found");
        }

        FinancialStatementEntity updated =
                toEntity(dto);
        updated.setId(existing.getId());

        return toDto(mongoTemplate.save(updated));
    }


    @Override
    public FinancialStatementEntityDto getById(
            String id) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(
                        id,
                        FinancialStatementEntity.class);

        if (entity == null) {
            throw new IllegalArgumentException(
                    "FinancialStatement not found");
        }

        return toDto(entity);
    }

    @Override
    public List<FinancialStatementEntityDto> getAll() {

        return mongoTemplate.findAll(
                        FinancialStatementEntity.class)
                .stream()
                .map(this::toDto)
                .toList();
    }


    @Override
    public void delete(String id) {

        Query query = Query.query(
                Criteria.where("_id").is(id));

        mongoTemplate.remove(
                query,
                FinancialStatementEntity.class);
    }



    private FinancialStatementEntity toEntity(
            FinancialStatementEntityDto dto) {

        FinancialStatementEntity entity =
                new FinancialStatementEntity();

        entity.setId(dto.getId());
        entity.setBorrowerName(dto.getBorrowerName());
        entity.setCurrency(dto.getCurrency());
        entity.setBorrowerClassification(
                dto.getBorrowerClassification());
        entity.setEvaluationYears(dto.getEvaluationYears());
        entity.setChecksAndBalances(
                dto.getChecksAndBalances());





        Map<String, SectionNodeEntity> bs =
                new LinkedHashMap<>();

        if (dto.getBalanceSheet() != null) {
            dto.getBalanceSheet().forEach((k, v) -> {
                SectionNodeEntity s =
                        new SectionNodeEntity();
                s.setName(v.getName());
                s.setLineItems(v.getLineItems());
                bs.put(k, s);
            });
        }
        entity.setBalanceSheet(bs);



        Map<String, SectionNodeEntity> pl =
                new LinkedHashMap<>();

        if (dto.getProfitAndLoss() != null) {
            dto.getProfitAndLoss().forEach((k, v) -> {
                SectionNodeEntity s =
                        new SectionNodeEntity();
                s.setName(v.getName());
                s.setLineItems(v.getLineItems());
                pl.put(k, s);
            });
        }
        entity.setProfitAndLoss(pl);



        Map<String, RatioCategoryEntity> ratioMap =
                new LinkedHashMap<>();

        if (dto.getFinancialRatios() != null) {
            dto.getFinancialRatios().forEach((k, v) -> {

                RatioCategoryEntity category =
                        new RatioCategoryEntity();
                category.setCategoryName(
                        v.getCategoryName());

                List<RatioEntity> ratioList =
                        v.getRatios() == null
                                ? List.of()
                                : v.getRatios()
                                .stream()
                                .map(r -> {
                                    RatioEntity e =
                                            new RatioEntity();
                                    e.setName(r.getName());
                                    e.setFormula(
                                            r.getFormula());
                                    e.setValues(
                                            r.getValues());
                                    return e;
                                })
                                .toList();

                category.setRatios(ratioList);
                ratioMap.put(k, category);
            });
        }
        entity.setFinancialRatios(ratioMap);

        return entity;
    }






    private FinancialStatementEntityDto toDto(
            FinancialStatementEntity entity) {

        FinancialStatementEntityDto dto =
                new FinancialStatementEntityDto();

        dto.setId(entity.getId());
        dto.setBorrowerName(entity.getBorrowerName());
        dto.setCurrency(entity.getCurrency());
        dto.setBorrowerClassification(
                entity.getBorrowerClassification());
        dto.setEvaluationYears(
                entity.getEvaluationYears());
        dto.setChecksAndBalances(
                entity.getChecksAndBalances());



        Map<String, SectionNodeEntityDto> bs =
                new LinkedHashMap<>();

        if (entity.getBalanceSheet() != null) {
            entity.getBalanceSheet().forEach((k, v) -> {
                SectionNodeEntityDto s =
                        new SectionNodeEntityDto();
                s.setName(v.getName());
                s.setLineItems(v.getLineItems());
                bs.put(k, s);
            });
        }
        dto.setBalanceSheet(bs);




        Map<String, SectionNodeEntityDto> pl =
                new LinkedHashMap<>();

        if (entity.getProfitAndLoss() != null) {
            entity.getProfitAndLoss().forEach((k, v) -> {
                SectionNodeEntityDto s =
                        new SectionNodeEntityDto();
                s.setName(v.getName());
                s.setLineItems(v.getLineItems());
                pl.put(k, s);
            });
        }
        dto.setProfitAndLoss(pl);




        Map<String, RatioCategoryEntityDto> ratioMap =
                new LinkedHashMap<>();

        if (entity.getFinancialRatios() != null) {
            entity.getFinancialRatios().forEach((k, v) -> {

                RatioCategoryEntityDto cat =
                        new RatioCategoryEntityDto();
                cat.setCategoryName(
                        v.getCategoryName());

                List<RatioEntityDto> ratios =
                        v.getRatios() == null
                                ? List.of()
                                : v.getRatios()
                                .stream()
                                .map(r -> {
                                    RatioEntityDto d =
                                            new RatioEntityDto();
                                    d.setName(r.getName());
                                    d.setFormula(
                                            r.getFormula());
                                    d.setValues(
                                            r.getValues());
                                    return d;
                                })
                                .toList();

                cat.setRatios(ratios);
                ratioMap.put(k, cat);
            });
        }
        dto.setFinancialRatios(ratioMap);

        return dto;
    }
}

