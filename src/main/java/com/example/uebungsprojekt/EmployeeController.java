package com.example.uebungsprojekt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping(path = "/emp")
public class EmployeeController {

    private final EmployeeRepository empRepo;

    public EmployeeController(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }


    @PostMapping()
    @ResponseBody Employee addNewEmp(@RequestBody Employee emp) {
        // Todo: replace with proper constructor paradigma
        return empRepo.save(emp);
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody void removeEmp(@PathVariable UUID id){
        empRepo.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody Employee updateEmp(@PathVariable UUID id,@RequestBody Employee emp) {
        return empRepo.updateEmployee(id, emp);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Employee> getAllEmps(){
        return empRepo.findAll();
    }


}
