package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.BorrowerDetailsDto;
import com.example.Orr.service.qualitative.BorrowerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrower")
public class BorrowerDetailsController {

    @Autowired
    private BorrowerDetailsService borrowerDetailsService;


    @GetMapping("/all")
    public ResponseEntity<List<BorrowerDetailsDto>> getAllBorrowers() {
        List<BorrowerDetailsDto> borrowers = borrowerDetailsService.findAll();
        return ResponseEntity.ok(borrowers);
    }

    @GetMapping("/{uUId}")
    public ResponseEntity<BorrowerDetailsDto> getBorrowerById(@PathVariable String uUId) {
        BorrowerDetailsDto borrower = borrowerDetailsService.findByUUId(uUId);
        if (borrower != null) {
            return ResponseEntity.ok(borrower);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<BorrowerDetailsDto> createBorrower(@RequestBody BorrowerDetailsDto borrowerDto) {
        BorrowerDetailsDto createdBorrower = borrowerDetailsService.create(borrowerDto);
        return ResponseEntity.ok(createdBorrower);
    }

    @PutMapping("/update/{uUId}")
    public ResponseEntity<BorrowerDetailsDto> updateBorrower(
            @PathVariable String uUId,
            @RequestBody BorrowerDetailsDto borrowerDto) {
        BorrowerDetailsDto updatedBorrower = borrowerDetailsService.updateByUUId(uUId, borrowerDto);
        if (updatedBorrower != null) {
            return ResponseEntity.ok(updatedBorrower);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{uUId}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable String uUId) {
        borrowerDetailsService.deleteByUUId(uUId);
        return ResponseEntity.noContent().build();
    }
}
