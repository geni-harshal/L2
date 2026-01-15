package com.example.Orr.dto.spreding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data

public class SectionNodeEntityDto {

    private String name;
    private Map<String, Map<Integer, Double>> lineItems;

}
