package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.AccountStatusDto;
import com.example.Orr.service.qualitative.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-status")
public class AccountStatusController {

    @Autowired
    private AccountStatusService accountStatusService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountStatusDto>> getAll() {
        return ResponseEntity.ok(accountStatusService.findAll());
    }

    @GetMapping("/{uUId}")
    public ResponseEntity<AccountStatusDto> getByUUId(
            @PathVariable String uUId
    ) {
        AccountStatusDto dto = accountStatusService.findByUUId(uUId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/{uUId}")
    public ResponseEntity<AccountStatusDto> create(
            @PathVariable String uUId,
            @RequestBody AccountStatusDto accountStatusDto
    ) {
        AccountStatusDto created =
                accountStatusService.create(uUId, accountStatusDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uUId}")
    public ResponseEntity<AccountStatusDto> update(
            @PathVariable String uUId,
            @RequestBody AccountStatusDto accountStatusDto
    ) {
        AccountStatusDto updated =
                accountStatusService.updateByUUId(uUId, accountStatusDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{uUId}")
    public ResponseEntity<Void> delete(
            @PathVariable String uUId
    ) {
        accountStatusService.deleteByUUId(uUId);
        return ResponseEntity.noContent().build();
    }
}
