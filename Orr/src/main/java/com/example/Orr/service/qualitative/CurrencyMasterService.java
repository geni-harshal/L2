package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.CurrencyMasterDto;

import java.util.List;

public interface CurrencyMasterService {
    CurrencyMasterDto create(CurrencyMasterDto dto);
    List<CurrencyMasterDto> findAll();
    CurrencyMasterDto findByCode(String currencyCode);
    CurrencyMasterDto update(String currencyCode, CurrencyMasterDto dto);
    void delete(String currencyCode);
}
