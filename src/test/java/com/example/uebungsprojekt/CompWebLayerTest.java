package com.example.uebungsprojekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@Import(CompanyModelAssembler.class)
public class CompWebLayerTest {


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentationExtension){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationExtension))
                .build();
    }

    @MockBean
    CompanyRepositoy compRepo;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getCompanies() throws Exception{

        when(compRepo.findAll()).thenReturn(List.of(new Company()));

        mockMvc.perform(get("/comp/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companyList", hasSize(1)))
                .andDo(document("companies"));
    }

    @Test
    public void getCompany() throws Exception{
        Company comp = new Company();
        when(compRepo.findById(comp.getId())).thenReturn(Optional.of(comp));

        mockMvc.perform(get("/comp/" + comp.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(comp.getId().toString()))
                .andDo(document("company"));

    }

    @Test
    public void getCompanyVatId() throws Exception{
        Company comp = new Company().setVatId("12345").setCountryCode("AT");
        when(compRepo.findById(comp.getId())).thenReturn(Optional.of(comp));

        mockMvc.perform(get("/comp/" + comp.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vatId").value(comp.getVatId()));
    }

    @Test
    public void getCompanyEmployees() throws Exception{
        ArrayList<Employee> listOfEmps = new ArrayList<>();
        listOfEmps.add(new Employee("Chris"));
        listOfEmps.add(new Employee("Anika"));


        Company comp = new Company().setEmployees(listOfEmps);

        when(compRepo.findById(comp.getId())).thenReturn(Optional.of(comp));

        mockMvc.perform(get("/comp/" + comp.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees").value(hasSize(2)));
    }


}
