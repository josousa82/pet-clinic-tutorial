package com.springframework.petclinictutorial.model.repositories;

import com.springframework.petclinictutorial.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.model.repositories
 **/
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
