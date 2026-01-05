package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.AccountConductDto;
import com.example.Orr.service.qualitative.AccountConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-conduct")
public class AccountConductController {

    @Autowired
    private AccountConductService accountConductService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountConductDto>> getAll() {
        return ResponseEntity.ok(accountConductService.findAll());
    }

    @GetMapping("/{uUId}")
    public ResponseEntity<AccountConductDto> getByUUId(
            @PathVariable String uUId
    ) {
        AccountConductDto dto = accountConductService.findByUUId(uUId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/{uUId}")
    public ResponseEntity<AccountConductDto> create(
            @PathVariable String uUId,
            @RequestBody AccountConductDto accountConductDto
    ) {
        AccountConductDto created =
                accountConductService.create(uUId, accountConductDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uUId}")
    public ResponseEntity<AccountConductDto> update(
            @PathVariable String uUId,
            @RequestBody AccountConductDto accountConductDto
    ) {
        AccountConductDto updated =
                accountConductService.updateByUUId(uUId, accountConductDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{uUId}")
    public ResponseEntity<Void> delete(
            @PathVariable String uUId
    ) {
        accountConductService.deleteByUUId(uUId);
        return ResponseEntity.noContent().build();
    }
}
