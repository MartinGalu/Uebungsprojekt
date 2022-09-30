package com.example.uebungsprojekt;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/comp")
public class CompanyController {

    private final CompanyModelAssembler assembler;

    private final CompanyRepositoy compRepo;

    public CompanyController(CompanyModelAssembler assembler, CompanyRepositoy compRepo) {
        this.assembler = assembler;
        this.compRepo = compRepo;
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<Company>> getCompanies(){
        List<EntityModel<Company>> companies = compRepo.findAll().stream().map(assembler::toModel).toList();

        return CollectionModel.of(companies, linkTo(methodOn(CompanyController.class).getCompanies()).withSelfRel());
    }

    @GetMapping("/{vatId}")
    public EntityModel<Company> getCompany(@PathVariable UUID vatId) {
        return assembler.toModel(compRepo.findById(vatId).orElseThrow());
    }

}
