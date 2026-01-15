package com.example.Orr.dto.spreding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data

public class RatioEntityDto {
    private String name;
    private String formula;
    private Map<Integer, Double> values;
}
