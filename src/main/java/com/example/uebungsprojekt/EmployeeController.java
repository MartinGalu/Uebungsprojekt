package com.example.uebungsprojekt;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/emp")
public class EmployeeController {

    private final EmployeeRepository empRepo;

    public EmployeeController(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }


    @PostMapping(path = "/add")
    public @ResponseBody String addNewEmp(@RequestParam String name, @RequestParam List<String> roles) {
        // Todo: replace with proper constructor paradigma
        Employee emp = new Employee();
        emp.setName(name);
        emp.setRoles(new ArrayList<>(roles));
        empRepo.save(emp);
        return "Saved";
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody int removeEmp(@RequestParam String name){
        Optional<Employee> emp = empRepo.findOne(Example.of(new Employee(name)));
        if(emp.isPresent()){
            empRepo.delete(emp.get());
            return 200;
        }
        return 400;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Employee> getAllEmps(){
        return empRepo.findAll();
    }


}
