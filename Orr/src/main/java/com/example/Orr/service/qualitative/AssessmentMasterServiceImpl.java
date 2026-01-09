package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AssessmentGroupDto;
import com.example.Orr.dto.qualitative.AssessmentMasterDto;
import com.example.Orr.dto.qualitative.AssessmentOptionDto;
import com.example.Orr.entity.qualitative.AssessmentGroup;
import com.example.Orr.entity.qualitative.AssessmentMaster;
import com.example.Orr.entity.qualitative.AssessmentOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssessmentMasterServiceImpl implements AssessmentMasterService {

    private static final String MASTER_ID = "MASTER_ASSESSMENT";

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public AssessmentMasterDto getAssessmentMaster() {
        return toMasterDto(fetchMaster());
    }

    @Override
    public AssessmentMasterDto getOASA() {
        return new AssessmentMasterDto(
                toGroupDtoMap(fetchMaster().getOASA()),
                null,
                null

        );
    }

    @Override
    public AssessmentMasterDto getSOAA() {
        return new AssessmentMasterDto(
                null,
                toGroupDtoMap(fetchMaster().getSOAA()),
                null
        );
    }

    @Override
    public AssessmentMasterDto getCOAA() {
        return new AssessmentMasterDto(
                null,
                null,
                toGroupDtoMap(fetchMaster().getCOAA())

        );
    }




    @Override
    public void addSubGroup(String section, String key, AssessmentGroupDto dto) {

        Query query = Query.query(Criteria.where("_id").is(MASTER_ID));

        Update update = new Update()
                .set(section + "." + key, toGroupEntity(dto));

        mongoTemplate.upsert(query, update, AssessmentMaster.class);
    }


    @Override
    public void updateSubGroup(String section, String key, AssessmentGroupDto dto) {

        Query query = Query.query(
                Criteria.where("_id").is(MASTER_ID)
                        .and(section + "." + key).exists(true)
        );

        Update update = new Update()
                .set(section + "." + key, toGroupEntity(dto));

        mongoTemplate.updateFirst(query, update, AssessmentMaster.class);
    }


    @Override
    public void deleteSubGroup(String section, String key) {

        Query query = Query.query(Criteria.where("_id").is(MASTER_ID));

        Update update = new Update().unset(section + "." + key);

        mongoTemplate.updateFirst(query, update, AssessmentMaster.class);
    }


    private AssessmentMaster fetchMaster() {
        AssessmentMaster master =
                mongoTemplate.findById(MASTER_ID, AssessmentMaster.class);

        if (master == null) {
            throw new RuntimeException("Assessment Master not found");
        }
        return master;
    }


    private AssessmentMasterDto toMasterDto(AssessmentMaster master) {
        return new AssessmentMasterDto(
                toGroupDtoMap(master.getOASA()),
                toGroupDtoMap(master.getSOAA()),
                toGroupDtoMap(master.getCOAA())
        );
    }

    private Map<String, AssessmentGroupDto> toGroupDtoMap(
            Map<String, AssessmentGroup> groupMap) {

        if (groupMap == null) return null;

        return groupMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> toGroupDto(e.getValue())
                ));
    }

    private AssessmentGroupDto toGroupDto(AssessmentGroup group) {
        return new AssessmentGroupDto(
                group.getSubgroupId(),
                group.getTitle(),
                group.getOptions()
                        .stream()
                        .map(o -> new AssessmentOptionDto(
                                o.getScore(),
                                o.getDescription()
                        ))
                        .collect(Collectors.toList())
        );
    }


    private AssessmentGroup toGroupEntity(AssessmentGroupDto dto) {
        return new AssessmentGroup(
                dto.getSubgroupId(),
                dto.getTitle(),
                dto.getOptions()
                        .stream()
                        .map(o -> new AssessmentOption(
                                o.getScore(),
                                o.getDescription()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
