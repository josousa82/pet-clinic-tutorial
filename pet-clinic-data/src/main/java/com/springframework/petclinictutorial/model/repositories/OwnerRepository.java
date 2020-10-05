package com.springframework.petclinictutorial.model.repositories;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.CrudService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.model.repositories
 **/

@Repository
public interface OwnerRepository extends CrudService<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);

}
