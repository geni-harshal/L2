package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.OwnerAdditionalSupportDto;
import com.example.Orr.entity.qualitative.OwnerAdditionalSupport;
import com.example.Orr.service.qualitative.OwnerAdditionalSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerAdditionalSupportServiceImpl implements OwnerAdditionalSupportService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<OwnerAdditionalSupportDto> findAll() {
        return mongoTemplate.findAll(OwnerAdditionalSupport.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerAdditionalSupportDto findByUUId(String uUId) {
        OwnerAdditionalSupport entity = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                OwnerAdditionalSupport.class
        );
        return toDto(entity);
    }

    @Override
    public OwnerAdditionalSupportDto create(String uUId, OwnerAdditionalSupportDto dto) {

        // prevent duplicate record
        OwnerAdditionalSupport existing = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                OwnerAdditionalSupport.class
        );

        if (existing != null) {
            throw new RuntimeException(
                    "OwnerAdditionalSupport already exists for UUID: " + uUId
            );
        }

        OwnerAdditionalSupport entity = new OwnerAdditionalSupport();
        entity.setUUId(uUId);
        entity.setPersonalNetWorthScore(dto.getPersonalNetWorthScore());

        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public OwnerAdditionalSupportDto updateByUUId(String uUId, OwnerAdditionalSupportDto dto) {

        Query query = Query.query(Criteria.where("uUId").is(uUId));
        Update update = new Update()
                .set("personalNetWorthScore", dto.getPersonalNetWorthScore());

        mongoTemplate.updateFirst(query, update, OwnerAdditionalSupport.class);

        return findByUUId(uUId);
    }

    @Override
    public void deleteByUUId(String uUId) {
        mongoTemplate.remove(
                Query.query(Criteria.where("uUId").is(uUId)),
                OwnerAdditionalSupport.class
        );
    }

    private OwnerAdditionalSupportDto toDto(OwnerAdditionalSupport entity) {
        if (entity == null) return null;

        OwnerAdditionalSupportDto dto = new OwnerAdditionalSupportDto();
        dto.setUUId(entity.getUUId());
        dto.setPersonalNetWorthScore(entity.getPersonalNetWorthScore());
        return dto;
    }
}
