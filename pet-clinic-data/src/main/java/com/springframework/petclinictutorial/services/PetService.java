package com.springframework.petclinictutorial.services;

import com.springframework.petclinictutorial.model.Pet;

import java.util.Set;

/**
 * Created by sousaJ on 25/08/2020
 * in package - com.springframework.petclinictutorial.services
 **/
public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
