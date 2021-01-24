package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.Visit;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by sousaJ on 24/01/2021
 * in package - com.springframework.petclinictutorial.controllers
 **/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VisitController {
    private final String VIEW_VISIT_CREATE_OR_UPDATE_FORM = "visits/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model){
        Pet pet = this.petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long id, Model model){
        return VIEW_VISIT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, @PathVariable("ownerId") Long ownerId, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return VIEW_VISIT_CREATE_OR_UPDATE_FORM;
        } else {
            visitService.addNewVisit(visit, model);
            return "redirect:/owners/" + ownerId;
        }
    }
}
