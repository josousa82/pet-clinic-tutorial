package com.springframework.petclinictutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets, Pet pet) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(pets != null) {
            this.pets = pets;
        }
        addPet(pet);
    }

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

//    @Getter(onMethod = @__( @JsonIgnore))
//    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet){
        if (pets == null)
            pets = new HashSet<>();
        this.pets.add(pet);
    }

    public Pet getPet(String name){
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew){
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                if (pet.getName().equalsIgnoreCase(name)) {
                    return pet;
                }
            }
        }
        return null;
    }
}
