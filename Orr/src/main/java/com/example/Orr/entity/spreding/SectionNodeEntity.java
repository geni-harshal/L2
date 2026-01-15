package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.Map;
@Data
public class SectionNodeEntity {

    private String name;

    // lineItem → (year → value)
    private Map<String, Map<Integer, Double>> lineItems;

}
