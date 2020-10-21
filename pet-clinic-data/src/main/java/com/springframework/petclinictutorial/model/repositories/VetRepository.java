package com.springframework.petclinictutorial.model.repositories;

import com.springframework.petclinictutorial.model.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.model.repositories
 **/
@Repository
public interface VetRepository extends CrudRepository<Vet, Long> {
}
