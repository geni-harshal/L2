package com.example.Orr.entity.spreding;

import lombok.Data;

import java.util.List;

@Data
public class RatioCategoryEntity {

    private String categoryName;
    private List<RatioEntity> ratios;

}
