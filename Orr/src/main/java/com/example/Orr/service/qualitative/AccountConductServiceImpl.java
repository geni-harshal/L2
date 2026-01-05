package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountConductDto;
import com.example.Orr.entity.qualitative.AccountConduct;
import com.example.Orr.service.qualitative.AccountConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountConductServiceImpl implements AccountConductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AccountConductDto> findAll() {
        return mongoTemplate.findAll(AccountConduct.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountConductDto findByUUId(String uUId) {
        AccountConduct entity = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                AccountConduct.class
        );
        return toDto(entity);
    }

    @Override
    public AccountConductDto create(String uUId, AccountConductDto dto) {

        // prevent duplicate record for same UUID
        AccountConduct existing = mongoTemplate.findOne(
                Query.query(Criteria.where("uUId").is(uUId)),
                AccountConduct.class
        );

        if (existing != null) {
            throw new RuntimeException("AccountConduct already exists for UUID: " + uUId);
        }

        AccountConduct entity = new AccountConduct();
        entity.setUUId(uUId);
        entity.setBounceCheques(dto.getBounceCheques());
        entity.setOngoingCreditRelationship(dto.getOngoingCreditRelationship());
        entity.setDelayInInstallments(dto.getDelayInInstallments());
        entity.setDelinquencyHistory(dto.getDelinquencyHistory());
        entity.setWriteOff(dto.getWriteOff());
        entity.setFraudLitigation(dto.getFraudLitigation());

        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public AccountConductDto updateByUUId(String uUId, AccountConductDto dto) {

        Query query = Query.query(Criteria.where("uUId").is(uUId));

        Update update = new Update()
                .set("bounceCheques", dto.getBounceCheques())
                .set("ongoingCreditRelationship", dto.getOngoingCreditRelationship())
                .set("delayInInstallments", dto.getDelayInInstallments())
                .set("delinquencyHistory", dto.getDelinquencyHistory())
                .set("writeOff", dto.getWriteOff())
                .set("fraudLitigation", dto.getFraudLitigation());

        mongoTemplate.updateFirst(query, update, AccountConduct.class);

        return findByUUId(uUId);
    }

    @Override
    public void deleteByUUId(String uUId) {
        mongoTemplate.remove(
                Query.query(Criteria.where("uUId").is(uUId)),
                AccountConduct.class
        );
    }

    private AccountConductDto toDto(AccountConduct entity) {
        if (entity == null) return null;

        AccountConductDto dto = new AccountConductDto();
        dto.setUUId(entity.getUUId());
        dto.setBounceCheques(entity.getBounceCheques());
        dto.setOngoingCreditRelationship(entity.getOngoingCreditRelationship());
        dto.setDelayInInstallments(entity.getDelayInInstallments());
        dto.setDelinquencyHistory(entity.getDelinquencyHistory());
        dto.setWriteOff(entity.getWriteOff());
        dto.setFraudLitigation(entity.getFraudLitigation());
        return dto;
    }
}
