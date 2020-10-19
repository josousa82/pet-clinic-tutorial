package com.springframework.petclinictutorial.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@SuperBuilder(toBuilder = true)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
