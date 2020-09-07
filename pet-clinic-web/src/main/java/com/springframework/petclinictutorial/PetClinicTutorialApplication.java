package com.springframework.petclinictutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PetClinicTutorialApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(PetClinicTutorialApplication.class, args);

        String testEnv = (String) ctx.getBean("testAwsEnv");

        System.out.println(testEnv);
    }

}
