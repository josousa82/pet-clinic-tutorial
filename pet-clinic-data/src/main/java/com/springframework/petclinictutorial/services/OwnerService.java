package com.springframework.petclinictutorial.services;

import com.springframework.petclinictutorial.model.Owner;

import java.util.Set;

/**
 * Created by sousaJ on 25/08/2020
 * in package - com.springframework.petclinictutorial.services
 **/
public interface OwnerService {
    Owner findByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
