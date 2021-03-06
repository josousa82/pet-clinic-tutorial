package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/

@Service
@Profile({"default", "map"})
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
        if (Objects.nonNull(object)) {
            return super.save(object);
        } else {
            throw new InvalidParameterException("Pet must not be null");
        }
    }

    @Override
    public void delete(Pet object) {

        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public Pet update(Pet object, Long aLong) {
        return null;
    }
}
