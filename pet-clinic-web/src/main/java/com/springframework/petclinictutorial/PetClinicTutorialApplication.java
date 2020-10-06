package com.springframework.petclinictutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.springframework.petclinictutorial.model.repositories", "com.springframework.petclinictutorial.bootstrap",
        "com.springframework.petclinictutorial.model", "com.springframework.petclinictutorial.configurations",
        "com.springframework.petclinictutorial.controllers",
        "com.springframework.petclinictutorial.services"})
public class PetClinicTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicTutorialApplication.class, args);
    }

}
