package com.springframework.petclinictutorial.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by sousaJ on 25/08/2020
 * in package - com.springframework.petclinictutorial.model
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain  = true)
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    public boolean isNew(){
        return this.id == null;
    }
}
