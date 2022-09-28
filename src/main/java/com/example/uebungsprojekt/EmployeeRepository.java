package com.example.uebungsprojekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>
{
    default Employee updateEmployee(UUID id, Employee emp) {
        return findById(id).map(employee -> {
            employee.setName(emp.getName());
            employee.setRoles(emp.getRoles());
            return save(emp);
        }).orElseGet(() -> {
            emp.setId(id);
            return save(emp);
        });
    }
}
