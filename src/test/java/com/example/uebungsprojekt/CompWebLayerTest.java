package com.example.uebungsprojekt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompWebLayerTest {

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
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getCompany() throws Exception{
        Company comp = new Company();
        when(compRepo.findById(comp.getId())).thenReturn(Optional.of(comp));

        mockMvc.perform(get("/comp/" + comp.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(comp.getId().toString()));

    }

    @Test
    public void getCompanyVatId() throws Exception{
        Company comp = new Company().setVatId("12345").setCountryCode("AT");
        when(compRepo.findById(comp.getId())).thenReturn(Optional.of(comp));

        mockMvc.perform(get("/comp/" + comp.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vatId").value(comp.getVatId()));
    }


}
