package com.example.uebungsprojekt;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmpWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository empRepo;

    @Test
    public void getAllEmps() throws Exception{
        Company comp = new Company().setRoles(List.of("Accountant"));
        Employee chris = new Employee("Chris the Accountant").joinCompany(comp, comp.getRoles().get(0));

        when(empRepo.findAll()).thenReturn(List.of(chris));

        mockMvc.perform(get("/emp/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    @Test
    public void getSingleEmp() throws Exception{
        Employee chris = new Employee("Chris");

        when(empRepo.findById(chris.getId())).thenReturn(Optional.of(chris));

        mockMvc.perform(get("/emp/" + chris.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(chris.getId().toString()))
                .andExpect(jsonPath("$.name").value(chris.getName()));
    }

    @Test
    public void addEmp() throws Exception {
        Company comp = new Company().setRoles(List.of("Accountant"));
        Employee chris = new Employee("Chris the Accountant").joinCompany(comp, comp.getRoles().get(0));

        String content = new Gson().toJson(chris);

        MockHttpServletRequestBuilder request = post("/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        when(empRepo.save(chris)).thenReturn(chris);

        this.mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void updateEmp() throws Exception {
        Company comp = new Company().setRoles(List.of("Accountant"));
        Employee chris = new Employee("Chris the Accountant").joinCompany(comp, comp.getRoles().get(0));
        String content = new Gson().toJson(chris);

        MockHttpServletRequestBuilder request = put("/emp/" + chris.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        when(empRepo.updateEmployee(eq(chris.getId()), any())).thenReturn(chris);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(chris.getName()));
    }

    @Test
    public void removeEmp() throws Exception{
        mockMvc.perform(delete("/emp/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }
}
