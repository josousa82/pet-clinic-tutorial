package com.springframework.petclinictutorial.model;

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
@EqualsAndHashCode
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

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public String toString() {
        return this.getName();
    }
}
