package com.example.uebungsprojekt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping(path = "/comp")
public class CompanyController {

    private final CompanyRepositoy compRepo;

    public CompanyController(CompanyRepositoy compRepo) {
        this.compRepo = compRepo;
    }

    @GetMapping("/")
    public @ResponseBody Iterable<Company> getCompanies(){
       return compRepo.findAll();
    }

    @GetMapping("/{vatId}")
    public @ResponseBody Company getCompany(@PathVariable UUID vatId) {
        return compRepo.findById(vatId).orElseThrow();
    }

}
