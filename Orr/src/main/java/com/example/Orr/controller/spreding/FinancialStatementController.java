package com.example.Orr.controller.spreding;

import com.example.Orr.dto.spreding.FinancialStatementEntityDto;
import com.example.Orr.dto.spreding.YearlyFinancialDataEntityDto;
import com.example.Orr.service.spreding.FinancialStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/financial-statements")
@RequiredArgsConstructor
public class FinancialStatementController {

    private final FinancialStatementService service;



    @PostMapping("/create")
    public ResponseEntity<String> createStatement(
            @RequestBody FinancialStatementEntityDto dto) {

        String id = service.create(dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialStatementEntityDto> getById(
            @PathVariable String id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FinancialStatementEntityDto>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FinancialStatementEntityDto> update(
            @PathVariable String id,
            @RequestBody FinancialStatementEntityDto dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/create-basic")
    public ResponseEntity<String> createBasicStatement(
            @RequestParam String borrowerName,
            @RequestParam String currency,
            @RequestParam String borrowerClassification) {

        return ResponseEntity.ok(
                service.createStatement(
                        borrowerName,
                        currency,
                        borrowerClassification
                )
        );
    }


    @PostMapping("/create/{id}/years/{year}")
    public ResponseEntity<Void> createYear(
            @PathVariable String id,
            @PathVariable Integer year,
            @RequestBody YearlyFinancialDataEntityDto dto) {

        service.createYear(id, year, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update//{id}/years/{year}")
    public ResponseEntity<FinancialStatementEntityDto> upsertYear(
            @PathVariable String id,
            @PathVariable Integer year,
            @RequestBody YearlyFinancialDataEntityDto dto) {

        return ResponseEntity.ok(
                service.upsertYear(id, year, dto)
        );
    }

    @GetMapping("/{id}/years/{year}")
    public ResponseEntity<YearlyFinancialDataEntityDto> getYear(
            @PathVariable String id,
            @PathVariable Integer year) {

        return ResponseEntity.ok(
                service.getYear(id, year)
        );
    }

    @GetMapping("/{id}/years")
    public ResponseEntity<Map<Integer, YearlyFinancialDataEntityDto>> getAllYears(
            @PathVariable String id) {

        return ResponseEntity.ok(
                service.getAllYears(id)
        );
    }

    @DeleteMapping("/delete/{id}/years/{year}")
    public ResponseEntity<Void> deleteYear(
            @PathVariable String id,
            @PathVariable Integer year) {

        service.deleteYear(id, year);
        return ResponseEntity.noContent().build();
    }
}
