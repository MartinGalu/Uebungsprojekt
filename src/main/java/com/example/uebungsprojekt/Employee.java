package com.example.uebungsprojekt;

import javax.persistence.*;
import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) && Objects.equals(name, employee.name) && Objects.equals(company, employee.company) && Objects.equals(roles, employee.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, company, roles);
    }

    @Override
    public String toString() {
        String company = getCompany() != null ? getCompany().getVatId() : "";
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", roles=" + roles +
                '}';
    }

    static class RolesNotFoundException extends Exception{
        RolesNotFoundException(){

        }
    }
}
