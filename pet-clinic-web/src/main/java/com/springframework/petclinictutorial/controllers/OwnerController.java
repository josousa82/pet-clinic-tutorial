package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


    @RequestMapping(value = {"/","/index", "/index.html"}, method = RequestMethod.GET)
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

}
