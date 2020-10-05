package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.PetTypeService;
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
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return new HashSet<>(super.findAll());
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if(Objects.nonNull(object)) {
            if (Objects.nonNull(object.getPets())) {
                object.getPets().forEach(pet -> {
                    if(Objects.nonNull(pet.getPetType())) {
                        if (Objects.isNull(pet.getPetType().getId())) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    }else {
                        throw new RuntimeException("Pet type is required");
                    }

                    if (Objects.isNull(pet.getId())){
                        petService.save(pet);
                    }
                });
            }
            return super.save(object);
        }else {
            throw new InvalidParameterException("Owner must not be null");
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
