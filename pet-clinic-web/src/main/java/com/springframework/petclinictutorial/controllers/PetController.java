package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.PetType;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created by sousaJ on 19/01/2021
 * in package - com.springframework.petclinictutorial.controllers.restControllers
 **/
@Controller
@RequestMapping("/owners/{ownerId}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PetController {

    private static final String VIEW_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;


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

    @GetMapping("/pets/new")
    public String initCreatePetForm(Owner owner, Model model){
        Pet pet = Pet.builder().build();
        owner.addPet(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationFormPet(Owner owner, @Valid Pet pet, BindingResult result, Model model){
        if(StringUtils.hasLength(pet.getName()) && Objects.nonNull(owner.getPet(pet.getName(), true)))
            result.rejectValue("name", "duplicate", "already exists");
            owner.addPet(pet);
            pet.setOwner(owner);
        if (result.hasErrors()){
            model.addAttribute("pet", pet);
            return VIEW_PETS_CREATE_OR_UPDATE_FORM;
        }else{
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdatePetForm(@PathVariable Long petId, Model model){
        model.addAttribute("pet", petService.findById(petId));
        model.addAttribute("petId", petService.findById(petId).getId());
        return VIEW_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(@Valid Pet pet, @PathVariable Map<String,String> val, BindingResult result, Owner owner, Model model){
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return  VIEW_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            petService.update(pet, Long.parseLong(val.get("petId")));
            return "redirect:/owners/" + owner.getId();
        }
    }

}
