package com.springframework.petclinictutorial.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by sousaJ on 16/08/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/

@Controller
public class IndexController {

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String getString(Model model){
        model.addAttribute("message", message);
        return "welcome";
    }
}
