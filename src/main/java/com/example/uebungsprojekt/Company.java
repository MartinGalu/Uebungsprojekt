package com.example.uebungsprojekt;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Company {

    @Id
    private UUID id;

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
}
