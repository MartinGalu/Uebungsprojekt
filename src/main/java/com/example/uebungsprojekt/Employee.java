package com.example.uebungsprojekt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Entity
public class Employee {

    public Employee() {
        this.id = UUID.randomUUID();
    }

    public Employee(String name){
        this();
        this.name = name;
    }

    @Id
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name="company_id", nullable = true)
    private Company company;

    @ElementCollection
    private List<String> roles = new ArrayList<>();


    public UUID getId() {
        return id;
    }

    public Employee setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Employee setRoles(List<String> roles) throws RolesNotFoundException {
        if (new HashSet<>(company.getRoles()).containsAll(roles)) {
            this.roles = roles;
        } else {
            throw new RolesNotFoundException();
        }
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Employee setCompany(Company company) {
        this.company = company;
        return this;
    }

    public Employee joinCompany(Company comp, String role) throws RolesNotFoundException {
        if (comp.getRoles().contains(role)) {
            setCompany(comp);
            roles.add(role);
        } else {
            throw new RolesNotFoundException();
        }

        return this;
    }

    static class RolesNotFoundException extends Exception{
        RolesNotFoundException(){

        }
    }
}
