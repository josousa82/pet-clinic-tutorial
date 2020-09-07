package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@Controller
@RequestMapping("/vets")
@CrossOrigin("http://localhost:8080")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping(value = {"/","/index", "/index.html"}, method = RequestMethod.GET)
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
