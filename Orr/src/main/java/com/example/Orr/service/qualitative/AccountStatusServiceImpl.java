package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountStatusDto;
import com.example.Orr.entity.qualitative.AccountStatus;
import com.example.Orr.service.qualitative.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AccountStatusDto> findAll() {
        return mongoTemplate.findAll(AccountStatus.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountStatusDto findByUUId(String uUId) {
        AccountStatus entity = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                AccountStatus.class
        );
        return toDto(entity);
    }

    @Override
    public AccountStatusDto create(String uUId, AccountStatusDto dto) {

        AccountStatus existing = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                AccountStatus.class
        );

        if (existing != null) {
            throw new RuntimeException(
                    "AccountStatus already exists for UUID: " + uUId
            );
        }

        AccountStatus entity = new AccountStatus();
        entity.setUUId(uUId);
        entity.setYearInBusiness(dto.getYearInBusiness());
        entity.setLocationOfBusiness(dto.getLocationOfBusiness());
        entity.setRelationshipAge(dto.getRelationshipAge());
        entity.setAuditorQuality(dto.getAuditorQuality());
        entity.setAuditorOpinion(dto.getAuditorOpinion());
        entity.setNationalizationScheme(dto.getNationalizationScheme());

        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public AccountStatusDto updateByUUId(String uUId, AccountStatusDto dto) {

        Query query = Query.query(Criteria.where("uUId").is(uUId));

        Update update = new Update()
                .set("yearInBusiness", dto.getYearInBusiness())
                .set("locationOfBusiness", dto.getLocationOfBusiness())
                .set("relationshipAge", dto.getRelationshipAge())
                .set("auditorQuality", dto.getAuditorQuality())
                .set("auditorOpinion", dto.getAuditorOpinion())
                .set("nationalizationScheme", dto.getNationalizationScheme());

        mongoTemplate.updateFirst(query, update, AccountStatus.class);

        return findByUUId(uUId);
    }

    @Override
    public void deleteByUUId(String uUId) {
        mongoTemplate.remove(
                Query.query(Criteria.where("uUId").is(uUId)),
                AccountStatus.class
        );
    }

    private AccountStatusDto toDto(AccountStatus entity) {
        if (entity == null) return null;

        AccountStatusDto dto = new AccountStatusDto();
        dto.setUUId(entity.getUUId());
        dto.setYearInBusiness(entity.getYearInBusiness());
        dto.setLocationOfBusiness(entity.getLocationOfBusiness());
        dto.setRelationshipAge(entity.getRelationshipAge());
        dto.setAuditorQuality(entity.getAuditorQuality());
        dto.setAuditorOpinion(entity.getAuditorOpinion());
        dto.setNationalizationScheme(entity.getNationalizationScheme());
        return dto;
    }
}
