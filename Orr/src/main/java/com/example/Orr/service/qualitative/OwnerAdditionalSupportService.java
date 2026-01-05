package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.OwnerAdditionalSupportDto;

import java.util.List;

public interface OwnerAdditionalSupportService {

    List<OwnerAdditionalSupportDto> findAll();

    OwnerAdditionalSupportDto findByUUId(String uUId);

    OwnerAdditionalSupportDto create(String uUId, OwnerAdditionalSupportDto dto);

    OwnerAdditionalSupportDto updateByUUId(String uUId, OwnerAdditionalSupportDto dto);

    void deleteByUUId(String uUId);
}
