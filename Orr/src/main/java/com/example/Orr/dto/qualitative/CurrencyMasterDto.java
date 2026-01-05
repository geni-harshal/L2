package com.example.Orr.dto.qualitative;

import lombok.Data;

@Data
public class CurrencyMasterDto {
    private String id;
    private String currencyCode;
    private String currencyName;
    private String currencySymbol;
    private boolean active;
}
