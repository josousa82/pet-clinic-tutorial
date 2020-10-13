package com.springframework.petclinictutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = {"com.springframework"})
public class PetClinicTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetClinicTutorialApplication.class, args);
    }

}
