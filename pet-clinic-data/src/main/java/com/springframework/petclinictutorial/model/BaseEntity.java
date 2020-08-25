package com.springframework.petclinictutorial.model;

import java.io.Serializable;

/**
 * Created by sousaJ on 25/08/2020
 * in package - com.springframework.petclinictutorial.model
 **/
public class BaseEntity implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
