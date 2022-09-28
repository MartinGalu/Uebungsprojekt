package com.example.uebungsprojekt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UebungsprojektApplicationTests {

    @Autowired
    private EmployeeController employeeController;

    @Test
    void contextLoads() {
        assertThat(employeeController).isNotNull();
    }

}