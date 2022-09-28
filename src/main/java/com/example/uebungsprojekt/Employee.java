package com.example.uebungsprojekt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Employee {

    public Employee(){
        this.id = UUID.randomUUID();
    }

    @Id
    private UUID id;

    private String name;

    @ElementCollection
    private List<String> roles = new ArrayList<>();

    public Employee(String name) {
        super();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
