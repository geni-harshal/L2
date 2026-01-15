package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.Map;
@Data
public class RatioEntity {

    private String name;
    private String formula;
    private Map<Integer, Double> values;

}
