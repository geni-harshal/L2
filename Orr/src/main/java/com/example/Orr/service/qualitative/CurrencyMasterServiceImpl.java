package com.example.Orr.service.qualitative;
import com.example.Orr.dto.qualitative.CurrencyMasterDto;
import com.example.Orr.entity.qualitative.CurrencyMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyMasterServiceImpl implements CurrencyMasterService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private CurrencyMasterDto toDto(CurrencyMaster entity) {
        CurrencyMasterDto dto = new CurrencyMasterDto();
        dto.setId(entity.getId());
        dto.setCurrencyCode(entity.getCurrencyCode());
        dto.setCurrencyName(entity.getCurrencyName());
        dto.setCurrencySymbol(entity.getCurrencySymbol());
        dto.setActive(entity.isActive());
        return dto;
    }

    private CurrencyMaster toEntity(CurrencyMasterDto dto) {
        CurrencyMaster entity = new CurrencyMaster();
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCurrencyName(dto.getCurrencyName());
        entity.setCurrencySymbol(dto.getCurrencySymbol());
        entity.setActive(dto.isActive());
        return entity;
    }

    @Override
    public CurrencyMasterDto create(CurrencyMasterDto dto) {

        Query query = new Query(Criteria.where("currencyCode").is(dto.getCurrencyCode()));
        if (mongoTemplate.exists(query, CurrencyMaster.class)) {
            throw new RuntimeException("Currency already exists");
        }

        CurrencyMaster entity = toEntity(dto);
        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public List<CurrencyMasterDto> findAll() {
        return mongoTemplate.findAll(CurrencyMaster.class)
                .stream().map(this::toDto).toList();
    }

    @Override
    public CurrencyMasterDto findByCode(String currencyCode) {
        Query query = new Query(Criteria.where("currencyCode").is(currencyCode));
        CurrencyMaster entity = mongoTemplate.findOne(query, CurrencyMaster.class);
        return entity != null ? toDto(entity) : null;
    }

    @Override
    public CurrencyMasterDto update(String currencyCode, CurrencyMasterDto dto) {
        Query query = new Query(Criteria.where("currencyCode").is(currencyCode));
        Update update = new Update()
                .set("currencyName", dto.getCurrencyName())
                .set("currencySymbol", dto.getCurrencySymbol())
                .set("active", dto.isActive());

        mongoTemplate.findAndModify(query, update, CurrencyMaster.class);
        return findByCode(currencyCode);
    }

    @Override
    public void delete(String currencyCode) {
        Query query = new Query(Criteria.where("currencyCode").is(currencyCode));
        mongoTemplate.remove(query, CurrencyMaster.class);
    }
}
