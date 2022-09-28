package com.example.uebungsprojekt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
