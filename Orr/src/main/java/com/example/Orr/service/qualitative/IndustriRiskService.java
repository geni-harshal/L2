package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.IndustriRiskDto;

import java.util.List;

public interface IndustriRiskService {

    List<IndustriRiskDto> findAll();

    IndustriRiskDto findByUUId(String uUId);

    IndustriRiskDto create(String uUId, IndustriRiskDto industriRiskDto);

    IndustriRiskDto updateByUUId(String uUId, IndustriRiskDto industriRiskDto);

    void deleteByUUId(String uUId);
}
