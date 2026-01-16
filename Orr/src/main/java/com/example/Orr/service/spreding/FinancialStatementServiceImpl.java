package com.example.Orr.service.spreding;

import com.example.Orr.dto.spreding.FinancialStatementEntityDto;
import com.example.Orr.dto.spreding.YearlyFinancialDataEntityDto;
import com.example.Orr.entity.spreding.FinancialStatementEntity;
import com.example.Orr.entity.spreding.YearlyFinancialDataEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialStatementServiceImpl implements FinancialStatementService {

    private final MongoTemplate mongoTemplate;

    public FinancialStatementServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /* =========================
       STATEMENT LEVEL CRUD
       ========================= */

    @Override
    public String create(FinancialStatementEntityDto dto) {

        FinancialStatementEntity entity = new FinancialStatementEntity();
        entity.setBorrowerName(dto.getBorrowerName());
        entity.setCurrency(dto.getCurrency());
        entity.setBorrowerClassification(dto.getBorrowerClassification());
        entity.setYears(new LinkedHashMap<>());

        mongoTemplate.save(entity);
        return entity.getId();
    }

    @Override
    public FinancialStatementEntityDto getById(String id) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(id, FinancialStatementEntity.class);

        if (entity == null) {
            throw new RuntimeException("Financial Statement not found");
        }

        return toStatementDto(entity);
    }

    @Override
    public List<FinancialStatementEntityDto> getAll() {

        return mongoTemplate.findAll(FinancialStatementEntity.class)
                .stream()
                .map(this::toStatementDto)
                .toList();
    }

    @Override
    public FinancialStatementEntityDto update(
            String id,
            FinancialStatementEntityDto dto) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(id, FinancialStatementEntity.class);

        if (entity == null) {
            throw new RuntimeException("Financial Statement not found");
        }

        entity.setBorrowerName(dto.getBorrowerName());
        entity.setCurrency(dto.getCurrency());
        entity.setBorrowerClassification(dto.getBorrowerClassification());

        mongoTemplate.save(entity);
        return toStatementDto(entity);
    }

    @Override
    public void delete(String id) {

        Query query = Query.query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, FinancialStatementEntity.class);
    }

    @Override
    public String createStatement(
            String borrowerName,
            String currency,
            String borrowerClassification) {

        FinancialStatementEntity entity = new FinancialStatementEntity();
        entity.setBorrowerName(borrowerName);
        entity.setCurrency(currency);
        entity.setBorrowerClassification(borrowerClassification);
        entity.setYears(new LinkedHashMap<>());

        mongoTemplate.save(entity);
        return entity.getId();
    }

    /* =========================
       YEAR LEVEL CRUD
       ========================= */

    @Override
    public FinancialStatementEntityDto upsertYear(
            String id,
            Integer year,
            YearlyFinancialDataEntityDto yearDto) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(id, FinancialStatementEntity.class);

        if (entity == null) {
            throw new RuntimeException("Financial Statement not found");
        }

        if (entity.getYears() == null) {
            entity.setYears(new LinkedHashMap<>());
        }

        entity.getYears().put(year, toYearEntity(yearDto));
        mongoTemplate.save(entity);

        // ✅ RETURN ENTIRE DATA
        return toStatementDto(entity);
    }

    @Override
    public void createYear(
            String statementId,
            Integer year,
            YearlyFinancialDataEntityDto dto) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(statementId, FinancialStatementEntity.class);

        if (entity == null) {
            throw new RuntimeException("Financial Statement not found");
        }

        if (entity.getYears() != null && entity.getYears().containsKey(year)) {
            throw new RuntimeException("Year already exists");
        }

        if (entity.getYears() == null) {
            entity.setYears(new LinkedHashMap<>());
        }

        entity.getYears().put(year, toYearEntity(dto));
        mongoTemplate.save(entity);
    }

    @Override
    public YearlyFinancialDataEntityDto getYear(
            String id,
            Integer year) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(id, FinancialStatementEntity.class);

        if (entity == null || entity.getYears() == null
                || !entity.getYears().containsKey(year)) {
            throw new RuntimeException("Year not found");
        }

        return toYearDto(entity.getYears().get(year));
    }

    @Override
    public void deleteYear(
            String id,
            Integer year) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(id, FinancialStatementEntity.class);

        if (entity == null || entity.getYears() == null) {
            throw new RuntimeException("Financial Statement not found");
        }

        entity.getYears().remove(year);
        mongoTemplate.save(entity);
    }

    @Override
    public Map<Integer, YearlyFinancialDataEntityDto> getAllYears(
            String statementId) {

        FinancialStatementEntity entity =
                mongoTemplate.findById(statementId, FinancialStatementEntity.class);

        if (entity == null || entity.getYears() == null) {
            throw new RuntimeException("Financial Statement not found");
        }

        Map<Integer, YearlyFinancialDataEntityDto> result = new LinkedHashMap<>();
        entity.getYears().forEach((year, yearEntity) ->
                result.put(year, toYearDto(yearEntity)));

        return result;
    }

    /* =========================
       MAPPING (INTEGRATED)
       ========================= */

    private YearlyFinancialDataEntity toYearEntity(
            YearlyFinancialDataEntityDto dto) {

        YearlyFinancialDataEntity entity = new YearlyFinancialDataEntity();
        entity.setBalanceSheet(dto.getBalanceSheet());
        entity.setProfitAndLoss(dto.getProfitAndLoss());
        entity.setFinancialRatios(dto.getFinancialRatios());
        entity.setChecksAndBalances(dto.getChecksAndBalances());
        return entity;
    }

    private YearlyFinancialDataEntityDto toYearDto(
            YearlyFinancialDataEntity entity) {

        YearlyFinancialDataEntityDto dto = new YearlyFinancialDataEntityDto();
        dto.setBalanceSheet(entity.getBalanceSheet());
        dto.setProfitAndLoss(entity.getProfitAndLoss());
        dto.setFinancialRatios(entity.getFinancialRatios());
        dto.setChecksAndBalances(entity.getChecksAndBalances());
        return dto;
    }

    private FinancialStatementEntityDto toStatementDto(
            FinancialStatementEntity entity) {

        FinancialStatementEntityDto dto = new FinancialStatementEntityDto();
        dto.setId(entity.getId());
        dto.setBorrowerName(entity.getBorrowerName());
        dto.setCurrency(entity.getCurrency());
        dto.setBorrowerClassification(entity.getBorrowerClassification());

        if (entity.getYears() != null) {
            Map<Integer, YearlyFinancialDataEntityDto> years =
                    new LinkedHashMap<>();
            entity.getYears().forEach((year, yearEntity) ->
                    years.put(year, toYearDto(yearEntity)));
            dto.setYears(years);
        }

        return dto;
    }
}
