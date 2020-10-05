package com.springframework.petclinictutorial.model.repositories;

import com.springframework.petclinictutorial.model.Specialty;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.model.repositories
 **/
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}