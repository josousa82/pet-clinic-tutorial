package com.springframework.petclinictutorial.model;

import java.util.Set;

/**
 * Created by sousaJ on 24/08/2020
 * in package - com.springframework.petclinictutorial.model
 **/
public class Vet extends Person {

    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
