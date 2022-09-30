package com.example.uebungsprojekt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Company {

    @Id
    private UUID id;
    private String vatId; //TODO: Can be changed to VatID object instead i suppose

    private String countryCode;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees = new ArrayList<>();

    @ElementCollection
    private List<String> roles = new ArrayList<>();

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


    public List<Employee> getEmployees() {
        return employees;
    }

    public Company setEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Company setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id.equals(company.id) && Objects.equals(vatId, company.vatId) && Objects.equals(countryCode, company.countryCode) && Objects.equals(employees, company.employees) && Objects.equals(roles, company.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vatId, countryCode, employees, roles);
    }
}
