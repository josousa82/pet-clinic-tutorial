package com.springframework.petclinictutorial.controllers.restControllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.PetType;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Created by sousaJ on 19/01/2021
 * in package - com.springframework.petclinictutorial.controllers.restControllers
 **/
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEW_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }
}
