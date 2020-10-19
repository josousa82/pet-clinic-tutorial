package com.springframework.petclinictutorial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by sousaJ on 12/09/2020
 * in package - com.springframework.petclinictutorial.model
 **/
@Getter
@Setter
@Entity
@Table(name = "specialties")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Specialty extends  BaseEntity {

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
