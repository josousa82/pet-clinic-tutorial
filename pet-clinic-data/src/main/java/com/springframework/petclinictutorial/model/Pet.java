package com.springframework.petclinictutorial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
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
@Accessors(chain  = true)
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits, Visit visit) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        if(Objects.isNull(visits)){
            this.visits = new HashSet<>();
        }
        addVisit(visit);
    }

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner  owner;


    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();


    public void addVisit(Visit visit){
        this.visits.add(visit);
    }

}
