package com.springframework.petclinictutorial.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sousaJ on 24/08/2020
 * in package - com.springframework.petclinictutorial.model
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }
    @Column(name = "name")
    private String name;

    public String toString() {
        return this.getName();
    }
}
