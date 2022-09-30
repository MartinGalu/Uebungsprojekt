package com.example.uebungsprojekt;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/emp")
public class EmployeeController {

    private final EmployeeRepository empRepo;

    public EmployeeController(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Employee>> getAllEmps(){
        List<EntityModel<Employee>> employees = empRepo.findAll().stream().map(employee -> EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).getEmp(employee.getId())).withSelfRel(), linkTo(methodOn(EmployeeController.class).getAllEmps()).withRel("employees"))).collect(Collectors.toList());


        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAllEmps()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<Employee> getEmp(@PathVariable UUID id) {
        Employee employee = empRepo.findById(id).orElseThrow();

        return EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).getEmp(id)).withSelfRel(), linkTo(methodOn(EmployeeController.class).getAllEmps()).withRel("employees"));
    }

    @PostMapping()
    Employee addNewEmp(@RequestBody Employee emp) {
        // Todo: replace with proper constructor paradigma
        return empRepo.save(emp);
    }

    @DeleteMapping(path="/{id}")
    void removeEmp(@PathVariable UUID id){
        empRepo.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmp(@PathVariable UUID id,@RequestBody Employee emp) {
        return empRepo.updateEmployee(id, emp);
    }

}
