package com.springframework.petclinictutorial.model;

/**
 * Created by sousaJ on 12/09/2020
 * in package - com.springframework.petclinictutorial.model
 **/
public class Specialty extends  BaseEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
