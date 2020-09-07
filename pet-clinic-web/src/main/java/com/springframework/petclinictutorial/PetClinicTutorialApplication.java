package com.springframework.petclinictutorial;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class PetClinicTutorialApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(PetClinicTutorialApplication.class, args);

        String testEnv = (String) ctx.getBean("testAwsEnv");

        log.info(testEnv);
    }

}
