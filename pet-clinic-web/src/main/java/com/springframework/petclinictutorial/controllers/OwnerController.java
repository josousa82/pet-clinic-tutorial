package com.springframework.petclinictutorial.controllers;

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

    @RequestMapping(value = {"", "/","/index", "/index.html"}, method = RequestMethod.GET)
    public String listOwners(Model model){
        return "owners/index";
    }
}
