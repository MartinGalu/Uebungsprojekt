package com.example.uebungsprojekt;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.rmi.server.UID;

@Entity
public class Company {

    @Id
    UID vatId;

    public Company(){
        this.vatId = new UID();
    }

}
