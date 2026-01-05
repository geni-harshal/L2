package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.BorrowerDetailsDto;
import com.example.Orr.entity.qualitative.BorrowerDetails;
import com.example.Orr.entity.qualitative.ClassificationMaster;
import com.example.Orr.entity.qualitative.CurrencyMaster;
import com.example.Orr.entity.qualitative.IndustryMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BorrowerDetailsServiceImpl implements BorrowerDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private BorrowerDetailsDto toDto(BorrowerDetails entity) {
       BorrowerDetailsDto borrowerDetailsDto=new BorrowerDetailsDto();
       borrowerDetailsDto.setUUId(entity.getUUId());
       borrowerDetailsDto.setBorrowerId(entity.getBorrowerId());
       borrowerDetailsDto.setId(entity.getId());
       borrowerDetailsDto.setName(entity.getName());
       borrowerDetailsDto.setCurrencyCode(entity.getCurrencyCode());
       borrowerDetailsDto.setAssessmentDate(entity.getAssessmentDate());
       borrowerDetailsDto.setRelationshipManager(entity.getRelationshipManager());
       borrowerDetailsDto.setClassificationCode(entity.getClassificationCode());
       borrowerDetailsDto.setIndustryCode(entity.getIndustryCode());

        CurrencyMaster currency = mongoTemplate.findOne(
                Query.query(Criteria.where("currencyCode").is(entity.getCurrencyCode())),
                CurrencyMaster.class
        );

        if (currency != null) {
            borrowerDetailsDto.setCurrencyName(currency.getCurrencyName());
            borrowerDetailsDto.setCurrencySymbol(currency.getCurrencySymbol());
        }
       return borrowerDetailsDto;
    }

    private BorrowerDetails toEntity(BorrowerDetailsDto dto) {
        BorrowerDetails borrowerDetails=new BorrowerDetails();
        borrowerDetails.setBorrowerId(dto.getBorrowerId());
        borrowerDetails.setUUId(dto.getUUId());
        borrowerDetails.setIndustryCode(dto.getIndustryCode());
        borrowerDetails.setName(dto.getName());
        borrowerDetails.setClassificationCode(dto.getClassificationCode());
        borrowerDetails.setAssessmentDate(dto.getAssessmentDate());
        borrowerDetails.setCurrencyCode(dto.getCurrencyCode());
        borrowerDetails.setRelationshipManager(dto.getRelationshipManager());
        return borrowerDetails;
    }

    @Override
    public List<BorrowerDetailsDto> findAll() {
        List<BorrowerDetails> list = mongoTemplate.findAll(BorrowerDetails.class);
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public BorrowerDetailsDto findByUUId(String uUId) {
        Query query = new Query(Criteria.where("uUId").is(uUId));
        BorrowerDetails entity = mongoTemplate.findOne(query, BorrowerDetails.class);
        return entity != null ? toDto(entity) : null;
    }

    @Override
    public BorrowerDetailsDto create(BorrowerDetailsDto dto) {

        // Currency
        Query currencyQuery = new Query(
                Criteria.where("currencyCode").is(dto.getCurrencyCode())
                        .and("active").is(true)
        );

        CurrencyMaster currency =
                mongoTemplate.findOne(currencyQuery, CurrencyMaster.class);

        if (currency == null) {
            throw new RuntimeException("Invalid or inactive currency");
        }



        // classification
        Query classificationQuery=new Query(
                Criteria.where("classificationCode").is(dto.getClassificationCode()).and("active").is(true)
        );

        ClassificationMaster classificationMaster=mongoTemplate.findOne(classificationQuery,ClassificationMaster.class);

        if(classificationMaster==null){
            throw new RuntimeException("Invalid Classification");
        }



        //Industry
        Query industryQuery=new Query(
                Criteria.where("industryCode").is(dto.getIndustryCode()).and("active").is(true)
        );

        IndustryMaster industryMaster=mongoTemplate.findOne(industryQuery,IndustryMaster.class);

        if(industryMaster==null){
            throw new RuntimeException("Invalid industry code");
        }



        BorrowerDetails entity = toEntity(dto);
        entity.setUUId(UUID.randomUUID().toString());

        mongoTemplate.save(entity);

        // enrich response
        BorrowerDetailsDto response = toDto(entity);
        response.setCurrencyName(currency.getCurrencyName());
        response.setCurrencySymbol(currency.getCurrencySymbol());

        response.setIndustryCode(industryMaster.getIndustryCode());
        response.setIndustryName(industryMaster.getIndustryName());

        response.setClassificationCode(classificationMaster.getClassificationCode());
        response.setClassificationName(classificationMaster.getClassificationName());
        response.setRiskWeight(classificationMaster.getRiskWeight());

        return response;
    }


    @Override
    public BorrowerDetailsDto updateByUUId(String uUId, BorrowerDetailsDto borrowerDetailsDto) {

        //currency
        Query currencyQuery = new Query(
                Criteria.where("currencyCode").is(borrowerDetailsDto.getCurrencyCode())
                        .and("active").is(true)
        );

        if (!mongoTemplate.exists(currencyQuery, CurrencyMaster.class)) {
            throw new RuntimeException("Invalid or inactive currency");
        }



        //classification
        Query classificationQuery=new Query(
                Criteria.where("classificationCode").is(borrowerDetailsDto.getClassificationCode()).and("active").is(true)
        );

        if(!mongoTemplate.exists(classificationQuery,ClassificationMaster.class)){
            throw new RuntimeException("Invalid Classification");
        }


        //Industry
        Query industryQuery=new Query(
                Criteria.where("industryCode").is(borrowerDetailsDto.getIndustryCode()).and("active").is(true)
        );
        if(!mongoTemplate.exists(industryQuery,IndustryMaster.class)){
            throw new RuntimeException("Invalid Industry");
        }


        Query query = new Query(Criteria.where("uUId").is(uUId));
        Update update = new Update()
                .set("name", borrowerDetailsDto.getName())
                .set("currencyCode", borrowerDetailsDto.getCurrencyCode())
                .set("classificationCode", borrowerDetailsDto.getClassificationCode())
                .set("assessmentDate", borrowerDetailsDto.getAssessmentDate())
                .set("relationshipManager", borrowerDetailsDto.getRelationshipManager())
                .set("industryCode", borrowerDetailsDto.getIndustryCode());

        mongoTemplate.findAndModify(query, update, BorrowerDetails.class);
        return findByUUId(uUId);
    }

    @Override
    public void deleteByUUId(String uUId) {
        Query query = new Query(Criteria.where("uUId").is(uUId));
        mongoTemplate.remove(query, BorrowerDetails.class);
    }

    @Override
    public BorrowerDetailsDto findByBorrowerId(Integer borrowerId) {
        return null;
    }
}
