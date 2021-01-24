package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Specialty;
import com.springframework.petclinictutorial.model.Vet;
import com.springframework.petclinictutorial.services.SpecialityService;
import com.springframework.petclinictutorial.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.services.map
 **/
@Service
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        super.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet save(Vet object) {
        if(!object.getSpecialties().isEmpty()){
            object.getSpecialties().forEach(specialty -> {
                if(Objects.isNull(specialty.getId())){
                    Specialty savedSpecialty = specialityService.save(specialty);
                    specialty.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet update(Vet object, Long aLong) {
        return null;
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

}
