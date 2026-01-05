package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.IndustriRiskDto;
import com.example.Orr.service.qualitative.IndustriRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industry-risk")
public class IndustriRiskController {

    @Autowired
    private IndustriRiskService industriRiskService;

    @GetMapping("/all")
    public ResponseEntity<List<IndustriRiskDto>> getAll() {
        return ResponseEntity.ok(industriRiskService.findAll());
    }

    @GetMapping("/{uUId}")
    public ResponseEntity<IndustriRiskDto> getByUUId(
            @PathVariable String uUId
    ) {
        IndustriRiskDto dto = industriRiskService.findByUUId(uUId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/{uUId}")
    public ResponseEntity<IndustriRiskDto> create(
            @PathVariable String uUId,
            @RequestBody IndustriRiskDto industriRiskDto
    ) {
        IndustriRiskDto created =
                industriRiskService.create(uUId, industriRiskDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uUId}")
    public ResponseEntity<IndustriRiskDto> update(
            @PathVariable String uUId,
            @RequestBody IndustriRiskDto industriRiskDto
    ) {
        IndustriRiskDto updated =
                industriRiskService.updateByUUId(uUId, industriRiskDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{uUId}")
    public ResponseEntity<Void> delete(
            @PathVariable String uUId
    ) {
        industriRiskService.deleteByUUId(uUId);
        return ResponseEntity.noContent().build();
    }
}
