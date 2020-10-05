package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.services.PetService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/

@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

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
        return super.save(object);
    }

    @Override
    public void delete(Pet object) {

        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }
}
