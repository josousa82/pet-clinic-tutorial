package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.PetType;
import com.springframework.petclinictutorial.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 12/09/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return new HashSet<>(super.findAll());
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public PetType save(PetType petType) {
        return super.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        super.delete(petType);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}
