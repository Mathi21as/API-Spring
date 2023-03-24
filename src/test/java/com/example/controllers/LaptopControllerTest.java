package com.example.controllers;

import com.example.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

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
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        assertTrue(laptops.size() >= 0);
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptop/2", Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        Laptop laptop = (Laptop) response.getBody();
        assertTrue(laptop != null);
        System.out.println("ID de la laptop: " + laptop.getId() + " manufacturer: " + laptop.getManufacturer());

    }

    @Test
    void create() {
        for (int i=1; i<=2; i++){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            String json = """
                {
                    "manufacturer":"HP $i creada desde un test",
                    "model":"fj2f",
                    "weight":980,
                    "inches":14,
                    "processorManufacturer":"INTEL",
                    "ramAmount":16
                }
                """.replace("$i", i+"");

            HttpEntity<String> request = new HttpEntity<>(json, headers);

            ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

            Laptop result = response.getBody();

            assertTrue(result != null);
            assertTrue(result.getId() > 0);
            assertEquals("HP " + i + " creada desde un test", result.getManufacturer());

        }
    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id":"2",
                    "manufacturer":"HP modificada desde un test",
                    "model":"fj2f",
                    "weight":700,
                    "inches":15,
                    "processorManufacturer":"AMD",
                    "ramAmount":12
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertTrue(result != null);
        assertTrue(result.getId() > 0);
        assertEquals("HP modificada desde un test", result.getManufacturer());
    }

    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, request, Laptop.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCodeValue());

    }

    @Test
    void deleteAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, request, Laptop.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCodeValue());
    }
}