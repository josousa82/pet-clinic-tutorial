package com.springframework.petclinictutorial.model.repositories;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.CrudService;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.model.repositories
 **/

public interface OwnerRepository extends CrudService<Owner, Long> {
}
