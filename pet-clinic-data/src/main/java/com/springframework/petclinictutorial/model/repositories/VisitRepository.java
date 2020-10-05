package com.springframework.petclinictutorial.model.repositories;

import com.springframework.petclinictutorial.model.Visit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.model.repositories
 **/
@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
