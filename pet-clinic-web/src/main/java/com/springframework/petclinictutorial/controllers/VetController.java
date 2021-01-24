package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sousaJ on 29/08/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VetController {

    private final VetService vetService;

    @GetMapping(value = {"/vets","/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }


}
