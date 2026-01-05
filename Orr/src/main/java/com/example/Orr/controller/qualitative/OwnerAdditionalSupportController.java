package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.OwnerAdditionalSupportDto;
import com.example.Orr.service.qualitative.OwnerAdditionalSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner-additional-support")
public class OwnerAdditionalSupportController {

    @Autowired
    private OwnerAdditionalSupportService ownerAdditionalSupportService;

    @GetMapping("/all")
    public ResponseEntity<List<OwnerAdditionalSupportDto>> getAll() {
        return ResponseEntity.ok(ownerAdditionalSupportService.findAll());
    }

    @GetMapping("/{uUId}")
    public ResponseEntity<OwnerAdditionalSupportDto> getByUUId(
            @PathVariable String uUId
    ) {
        OwnerAdditionalSupportDto dto = ownerAdditionalSupportService.findByUUId(uUId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create/{uUId}")
    public ResponseEntity<OwnerAdditionalSupportDto> create(
            @PathVariable String uUId,
            @RequestBody OwnerAdditionalSupportDto ownerAdditionalSupportDto
    ) {
        OwnerAdditionalSupportDto created =
                ownerAdditionalSupportService.create(uUId, ownerAdditionalSupportDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{uUId}")
    public ResponseEntity<OwnerAdditionalSupportDto> update(
            @PathVariable String uUId,
            @RequestBody OwnerAdditionalSupportDto ownerAdditionalSupportDto
    ) {
        OwnerAdditionalSupportDto updated =
                ownerAdditionalSupportService.updateByUUId(uUId, ownerAdditionalSupportDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{uUId}")
    public ResponseEntity<Void> delete(
            @PathVariable String uUId
    ) {
        ownerAdditionalSupportService.deleteByUUId(uUId);
        return ResponseEntity.noContent().build();
    }
}
