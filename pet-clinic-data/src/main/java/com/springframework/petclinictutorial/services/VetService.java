package com.springframework.petclinictutorial.services;

import com.springframework.petclinictutorial.model.Vet;

import java.util.Set;
/**
 * Created by sousaJ on 25/08/2020
 * in package - com.springframework.petclinictutorial.services
 **/
public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
