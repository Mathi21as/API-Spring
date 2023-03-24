package com.example.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${application.saludo}")
    String saludo;

    @GetMapping("/")
    public String hola(){
        return """
                <pre style="font-size:20px">
                    """ + saludo + """
                    
                    Prueba los endpoints:
                      + /api/laptops
                      + /api/laptop/'id' (reemplazar 'id' con un numero del 1 al 3)
                    
                    Desde postman:
                      + Metodo POST: /api/laptops | Para agregar una nueva laptop
                      + Metodo PUT: /api/laptops | Para modificar los datos de una laptop
                      + Metodo DELETE: /api/laptops/'id' | Para eliminar una laptop
                      + Metodo DELETE: /api/laptops | Para elminar todas las laptops
                </pre>
                """;
    }
}
