package com.springframework.petclinictutorial.services.springdatajpa;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.repositories.OwnerRepository;
import com.springframework.petclinictutorial.model.repositories.PetRepository;
import com.springframework.petclinictutorial.model.repositories.PetTypeRepository;
import com.springframework.petclinictutorial.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.services.springdatajpa
 **/
@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;


    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }


    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }

    @Override
    public Set<Owner> findAll() {
        return new HashSet<>(ownerRepository.findAll());
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
