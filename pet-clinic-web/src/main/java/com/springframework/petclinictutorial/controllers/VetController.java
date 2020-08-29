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
public class VetController {


    @RequestMapping(value = {"/vets","/vets/index", "/vets/index.html"}, method = RequestMethod.GET)
    public String listVets(Model model){

        return "vets/index";
    }
}
