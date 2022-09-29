package com.example.uebungsprojekt;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Company {

    @Id
    private UUID id;
    private String vatId; //TODO: Can be changed to VatID object instead i suppose

    private String countryCode;

    public Company(){
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public Company setId(UUID vatId) {
        this.id = vatId;
        return this;
    }

    public String getVatId() {
        return countryCode + "U" + vatId;
    }

    public Company setVatId(String vatId) {
        this.vatId = vatId;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Company setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

}
