package com.springframework.petclinictutorial.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by sousaJ on 12/09/2020
 * in package - com.springframework.petclinictutorial.model
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Builder
    public Visit(Long id, LocalDate date, String description, Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public LocalDate getDate() {
        return date;
    }
}
