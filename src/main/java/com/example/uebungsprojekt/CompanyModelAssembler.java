package com.example.uebungsprojekt;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyModelAssembler implements RepresentationModelAssembler<Company, EntityModel<Company>> {
    @Override
    public EntityModel<Company> toModel(Company company) {
        return EntityModel.of(company,
                linkTo(methodOn(CompanyController.class).getCompanies()).withSelfRel(),
                linkTo(methodOn(CompanyController.class).getCompany(company.getId())).withSelfRel());
    }
}
