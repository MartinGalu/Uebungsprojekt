package com.example.uebungsprojekt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(properties = {
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
        "spring.datasource.url=jdbc:tc:postgresql:///postgres"})
class UebungsprojektApplicationTests {

    @Test
    void contextLoads() {
    }

}