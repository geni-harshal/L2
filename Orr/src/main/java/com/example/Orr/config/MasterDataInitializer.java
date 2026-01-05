package com.example.Orr.config;

import com.example.Orr.entity.qualitative.ClassificationMaster;
import com.example.Orr.entity.qualitative.CurrencyMaster;
import com.example.Orr.entity.qualitative.IndustryMaster;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
public class MasterDataInitializer implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    public MasterDataInitializer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {
        initCurrencyMaster();
        initIndustryMaster();
        initClassificationMaster();
    }


    private void initCurrencyMaster() {
        insertCurrencyIfNotExists("INR", "Indian Rupee", "₹");
        insertCurrencyIfNotExists("USD", "US Dollar", "$");
        insertCurrencyIfNotExists("SAR","Saudi Rial","﷼");
    }

    private void insertCurrencyIfNotExists(
            String currencyCode,
            String currencyName,
            String currencySymbol
    ) {
        Query query = new Query(Criteria.where("currencyCode").is(currencyCode));
        if (!mongoTemplate.exists(query, CurrencyMaster.class)) {
            CurrencyMaster cm =
                    new CurrencyMaster(currencyCode, currencyName, currencySymbol, true);
            mongoTemplate.save(cm);
        }
    }




    private void initIndustryMaster() {
        insertIndustryIfNotExists("manufacturing", "Manufacturing");
        insertIndustryIfNotExists("IT services", "IT Services");
        insertIndustryIfNotExists("agriculture", "Agriculture");
        insertIndustryIfNotExists("healthcare","HealthCare");
    }

    private void insertIndustryIfNotExists(
            String industryCode,
            String industryName
    ) {
        Query query = new Query(Criteria.where("industryCode").is(industryCode));
        if (!mongoTemplate.exists(query, IndustryMaster.class)) {
            IndustryMaster industry = new IndustryMaster();
            industry.setIndustryCode(industryCode);
            industry.setIndustryName(industryName);
            industry.setActive(true);
            mongoTemplate.save(industry);
        }
    }




    private void initClassificationMaster() {
        insertClassificationIfNotExists("micro", "Micro", 25);
        insertClassificationIfNotExists("small", "Small", 50);
        insertClassificationIfNotExists("medium", "Medium", 75);
    }

    private void insertClassificationIfNotExists(
            String classificationCode,
            String classificationName,
            Integer riskWeight
    ) {
        Query query =
                new Query(Criteria.where("classificationCode").is(classificationCode));

        if (!mongoTemplate.exists(query, ClassificationMaster.class)) {
            ClassificationMaster cm = new ClassificationMaster();
            cm.setClassificationCode(classificationCode);
            cm.setClassificationName(classificationName);
            cm.setRiskWeight(riskWeight);
            cm.setActive(true);
            mongoTemplate.save(cm);
        }
    }
}
