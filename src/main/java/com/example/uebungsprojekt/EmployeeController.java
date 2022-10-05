package com.example.uebungsprojekt;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/emp")
public class EmployeeController {
    private final EmployeeRepository empRepo;

    public EmployeeController(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    @GetMapping()
    public List<Employee> getAllEmps(){
        return empRepo.findAll();
    }

    @GetMapping("/{id}")
    Employee getEmp(@PathVariable UUID id) {
        return empRepo.findById(id).orElseThrow();
    }

    @PostMapping()
    Employee addNewEmp(@RequestBody Employee emp) {
        return empRepo.save(emp);
    }

    @DeleteMapping(path="/{id}")
    void removeEmp(@PathVariable UUID id){
        empRepo.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmp(@PathVariable UUID id, @RequestBody Employee emp) {
        return empRepo.updateEmployee(id, emp);
    }

}
