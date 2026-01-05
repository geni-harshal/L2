package com.example.Orr.entity.qualitative;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "currency_master")
@Data
public class CurrencyMaster {

    @Id
    private String id;
    @Indexed(unique = true)
    private String currencyCode;
    private String currencyName;
    private String currencySymbol;
    private boolean active = true;


    public CurrencyMaster() {
    }

    public CurrencyMaster(String currencyCode, String currencyName, String currencySymbol, boolean active) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
        this.active = active;
    }

}

