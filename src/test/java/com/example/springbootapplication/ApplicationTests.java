package com.example.springbootapplication;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void devContextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void prodContextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is production", forEntity.getBody());
    }

}
