package com.example.uebungsprojekt;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Employee chris = new Employee("Chris the Accountant");
        chris.setRoles(Collections.singletonList("Accountant"));

        doReturn(List.of(chris)).when(empRepo).findAll();


        this.mockMvc.perform(get("/emp/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void addEmp() throws Exception {
        String content = new Gson().toJson(new Employee("Test"));

        MockHttpServletRequestBuilder request = post("/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        this.mockMvc.perform(request).andExpect(status().isOk());
    }
}
