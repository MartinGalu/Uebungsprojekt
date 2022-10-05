package com.example.uebungsprojekt;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/comp")
public class CompanyController {

    private final CompanyRepositoy compRepo;

    public CompanyController(CompanyRepositoy compRepo) {
        this.compRepo = compRepo;
    }

    @GetMapping("/")
    public List<Company> getCompanies(){
        return compRepo.findAll();
    }

    @GetMapping("/{vatId}")
    public Optional<Company> getCompany(@PathVariable UUID vatId) {
        return compRepo.findById(vatId);
    }

}
