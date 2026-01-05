package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.BorrowerDetailsDto;

import java.util.List;

public interface BorrowerDetailsService {

    List<BorrowerDetailsDto> findAll();

    // 🔑 PRIMARY
    BorrowerDetailsDto findByUUId(String uUId);

    BorrowerDetailsDto create(BorrowerDetailsDto borrowerDetailsDto);

    BorrowerDetailsDto updateByUUId(String uUId, BorrowerDetailsDto borrowerDetailsDto);

    void deleteByUUId(String uUId);

    // 🔎 OPTIONAL (legacy / external lookup)
    BorrowerDetailsDto findByBorrowerId(Integer borrowerId);
}
