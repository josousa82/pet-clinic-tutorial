package com.springframework.petclinictutorial.services;

import com.springframework.petclinictutorial.model.Visit;
import org.springframework.ui.Model;

/**
 * Created by sousaJ on 05/10/2020
 * in package - com.springframework.petclinictutorial.services
 **/
public interface VisitService extends CrudService<Visit, Long> {
    void addNewVisit(Visit visit, Model model);
}
