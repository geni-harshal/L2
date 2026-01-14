package com.example.Orr.controller.qualitative;

import com.example.Orr.dto.qualitative.AssessmentGroupDto;
import com.example.Orr.dto.qualitative.AssessmentMasterDto;
import com.example.Orr.service.qualitative.AssessmentMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessment-master")
public class AssessmentMasterController {

    @Autowired
    private AssessmentMasterService service;

    @GetMapping
    public AssessmentMasterDto getAll() {
        return service.getAssessmentMaster();
    }

    @GetMapping("/{section}/{subgroupId}")
    public ResponseEntity<AssessmentGroupDto> findBySectionAndSubgroupId(
            @PathVariable String section,
            @PathVariable String subgroupId) {

        AssessmentGroupDto response =
                service.findBySectionAndSubgroupId(section, subgroupId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/{section}/{key}")
    public ResponseEntity<?> addSubGroup(
            @PathVariable String section,
            @PathVariable String key,
            @RequestBody AssessmentGroupDto dto) {

        service.addSubGroup(section, key, dto);
        return ResponseEntity.ok("Subgroup added successfully");
    }

    @PutMapping("/update/{section}/{key}")
    public ResponseEntity<?> updateSubGroup(
            @PathVariable String section,
            @PathVariable String key,
            @RequestBody AssessmentGroupDto dto) {

        service.updateSubGroup(section, key, dto);
        return ResponseEntity.ok("Subgroup updated successfully");
    }

    @DeleteMapping("/delete/{section}/{key}")
    public ResponseEntity<?> deleteSubGroup(
            @PathVariable String section,
            @PathVariable String key) {

        service.deleteSubGroup(section, key);
        return ResponseEntity.ok("Subgroup deleted successfully");
    }
}
