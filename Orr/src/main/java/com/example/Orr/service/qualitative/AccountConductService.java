package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountConductDto;

import java.util.List;

public interface AccountConductService {

    List<AccountConductDto> findAll();

    // 🔑 MAIN FETCH (shared UUID with BorrowerDetails)
    AccountConductDto findByUUId(String uUId);

    // create using existing borrower UUID
    AccountConductDto create(String uUId, AccountConductDto accountConductDto);

    AccountConductDto updateByUUId(String uUId, AccountConductDto accountConductDto);

    void deleteByUUId(String uUId);
}
