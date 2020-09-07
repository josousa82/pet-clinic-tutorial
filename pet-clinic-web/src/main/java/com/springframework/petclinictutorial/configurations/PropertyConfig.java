package com.springframework.petclinictutorial.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by sousaJ on 07/09/2020
 * in package - com.springframework.petclinictutorial.configurations
 **/
@Configuration
public class PropertyConfig {
    @Autowired
    Environment environment;

    @Bean
    public String testAwsEnv(){
        return environment.getProperty("AWS_ACCESS_KEY_ID");

    }
}
