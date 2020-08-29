package com.springframework.petclinictutorial.services;

import com.springframework.petclinictutorial.model.Owner;

/**
 * Created by sousaJ on 25/08/2020
 * in package - com.springframework.petclinictutorial.services
 **/
public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
