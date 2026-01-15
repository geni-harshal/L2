package com.example.Orr.dto.spreding;

import com.example.Orr.entity.spreding.RatioEntity;
import lombok.Data;

import java.util.List;
@Data

public class RatioCategoryEntityDto {
    private String categoryName;
    private List<RatioEntityDto> ratios;


}
