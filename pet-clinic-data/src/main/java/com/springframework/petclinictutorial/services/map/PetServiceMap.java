package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.services.CrudService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return new HashSet<>(super.findAll());
    }

    @Override
    public Pet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public Pet save(Pet object) {
        return null;
    }

    @Override
    public void delete(Pet object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
