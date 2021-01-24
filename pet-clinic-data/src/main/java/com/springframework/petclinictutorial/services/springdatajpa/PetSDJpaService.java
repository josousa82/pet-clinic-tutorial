package com.springframework.petclinictutorial.services.springdatajpa;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.repositories.PetRepository;
import com.springframework.petclinictutorial.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }


    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet update(Pet updatedPet, Long id) {
       return petRepository.findById(id).map(pet -> {
        pet.setName(updatedPet.getName())
           .setPetType(updatedPet.getPetType())
           .setBirthDate(updatedPet.getBirthDate());
        return petRepository.save(pet);
       }).orElseThrow(() -> new RuntimeException("Not possible to update Pet."));
    }
}
