package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerController {

    private static final String VIEW_OWNER_CREATE_UPDATE_FORM = "/owners/createOrUpdateOwnerForm";
    public static final String  VIEW_REDIRECT_OWNERS = "redirect:/owners/";
    private final OwnerService ownerService;

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = { "/owners/","/owners/index", "/owners/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping({"/owners/find"})
    public String findOwners(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView getOwnerDetailsById(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }


    @GetMapping("/owners/getByLastName")
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){
        if(Objects.isNull(owner.getLastName())){
            owner.setLastName("");
        }

        List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (owners.isEmpty()){
            bindingResult.rejectValue("lastName", "notFound", "Not found");
            return "/owners/findOwners";

        }else if (owners.size() == 1){
            owner = owners.iterator().next();
            return VIEW_REDIRECT_OWNERS + owner.getId();

        }else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/new")
    public String initCreationForm(Model model){
       model.addAttribute("owner", Owner.builder().build());
       return VIEW_OWNER_CREATE_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result){
        if (result.hasErrors()) {
            return VIEW_OWNER_CREATE_UPDATE_FORM;
        } else {
           Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEW_OWNER_CREATE_UPDATE_FORM;
    }

    @PostMapping("owners/{ownerId}/edit")
    public String processUpdateOwner(@Valid Owner owner, @PathVariable("ownerId") Long ownerId, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return VIEW_OWNER_CREATE_UPDATE_FORM;
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
