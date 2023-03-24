package com.example.controllers;

import com.example.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        this.restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + this.port);
        this.testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    @Test
    void hola() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/", String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("""
                <pre style="font-size:20px">
                Hola desde test
                    Prueba los endpoints:
                      + /api/laptops
                      + /api/laptop/'id' (reemplazar 'id' con un numero del 1 al 3)
                    
                    Desde postman:
                      + Metodo POST: /api/laptops | Para agregar una nueva laptop
                      + Metodo PUT: /api/laptops | Para modificar los datos de una laptop
                      + Metodo DELETE: /api/laptops/'id' | Para eliminar una laptop
                      + Metodo DELETE: /api/laptops | Para elminar todas las laptops
                </pre>
                """, response.getBody());
    }
}