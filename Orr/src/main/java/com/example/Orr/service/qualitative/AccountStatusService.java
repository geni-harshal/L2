package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountStatusDto;

import java.util.List;

public interface AccountStatusService {

    List<AccountStatusDto> findAll();

    AccountStatusDto findByUUId(String uUId);

    AccountStatusDto create(String uUId, AccountStatusDto accountStatusDto);

    AccountStatusDto updateByUUId(String uUId, AccountStatusDto accountStatusDto);

    void deleteByUUId(String uUId);
}
