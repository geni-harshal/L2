package com.example.Orr.controller.spreding;


import com.example.Orr.dto.spreding.FinancialStatementEntityDto;
import com.example.Orr.service.spreding.FinancialStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-statements")
@RequiredArgsConstructor
public class FinancialStatementController {

    private final FinancialStatementService service;


    @PostMapping("/create")
    public ResponseEntity<FinancialStatementEntityDto> create(
            @RequestBody FinancialStatementEntityDto dto) {

        FinancialStatementEntityDto created =
                service.create(dto);

        return new ResponseEntity<>(
                created,
                HttpStatus.CREATED);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<FinancialStatementEntityDto> update(
            @PathVariable String id,
            @RequestBody FinancialStatementEntityDto dto) {

        FinancialStatementEntityDto updated =
                service.update(id, dto);

        return ResponseEntity.ok(updated);
    }



    @GetMapping("/{id}")
    public ResponseEntity<FinancialStatementEntityDto> getById(
            @PathVariable String id) {

        FinancialStatementEntityDto dto =
                service.getById(id);

        return ResponseEntity.ok(dto);
    }



    @GetMapping("/all")
    public ResponseEntity<List<FinancialStatementEntityDto>> getAll() {

        return ResponseEntity.ok(
                service.getAll());
    }



    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String id) {

        service.delete(id);
    }
}
