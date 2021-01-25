package com.springframework.petclinictutorial.services.springdatajpa;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.Visit;
import com.springframework.petclinictutorial.model.repositories.VisitRepository;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.services.springdatajpa
 **/

@Service
@Profile("springdatajpa")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;
    private final PetService petService;

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {

        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public Visit update(Visit object, Long aLong) {
        return null;
    }

    public void addNewVisit(Visit visit, Model model) {
        Pet pet = (Pet) model.getAttribute("pet");
        visit.setPet(pet);
        this.save(visit);
        petService.save(pet);
    }
}
