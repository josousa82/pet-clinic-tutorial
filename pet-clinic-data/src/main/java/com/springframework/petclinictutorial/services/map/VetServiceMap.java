package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Vet;
import com.springframework.petclinictutorial.services.VetService;

import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
