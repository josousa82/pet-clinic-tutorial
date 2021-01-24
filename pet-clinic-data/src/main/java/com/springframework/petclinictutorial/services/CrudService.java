package com.springframework.petclinictutorial.services;

import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services
 **/
public interface CrudService<T, ID> {
    Set<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
    T update(T object, ID id);
}
