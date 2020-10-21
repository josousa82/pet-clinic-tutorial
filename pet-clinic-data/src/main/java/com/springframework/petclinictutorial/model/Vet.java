package com.springframework.petclinictutorial.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by sousaJ on 24/08/2020
 * in package - com.springframework.petclinictutorial.model
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Specialty> specialties, Specialty specialty) {
        super(id, firstName, lastName);

        if(Objects.isNull(specialties)){
            this.specialties = new HashSet<>();
        }

        addSpeciality(specialty);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
                inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

    public void addSpeciality(Specialty specialty){
        this.specialties.add(specialty);
    }
}
