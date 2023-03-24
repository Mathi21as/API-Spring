package com.example;

import com.example.entities.Laptop;
import com.example.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRestDataJpaObApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringRestDataJpaObApplication.class, args);

		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);

		//instanciar objetos laptop
		Laptop laptop = new Laptop(null, "HP", "h34p", 890, 14.5, "AMD",16);
		Laptop laptop1 = new Laptop(null, "ASUS", "z4k2", 790, 15.0, "INTEL",8);
		Laptop laptop2 = new Laptop(null, "DELL", "D902l", 930, 15.0, "AMD",20);

		//Guardar laptops en la bd
		laptopRepository.save(laptop);
		laptopRepository.save(laptop1);
		laptopRepository.save(laptop2);
	}

}
