package com.springframework.petclinictutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetClinicTutorialApplication {

    public static void main(String[] args) {

        SpringApplication.run(PetClinicTutorialApplication.class, args);
        System.out.println(System.getProperty("AWS_ACCESS_KEY_ID"));

    }

}
