package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = {"", "/","/index", "/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){
        if(Objects.isNull(owner.getLastName())){
            owner.setLastName("");
        }

        List<Owner> owners = ownerService.findAllByLastName(owner.getLastName());

        if (owners.isEmpty()){
            bindingResult.rejectValue("Last name", "Not found", "Not found");
            return "owners/findOwners";

        }else if (owners.size() == 1){
            owner = owners.iterator().next();
            return "redirect:/owners/" + owner.getId();

        }else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping({"/find"})
    public String findOwners(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView getOwnerDetails(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }
}
