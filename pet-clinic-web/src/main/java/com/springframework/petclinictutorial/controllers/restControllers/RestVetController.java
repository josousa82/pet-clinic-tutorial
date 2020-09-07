package com.springframework.petclinictutorial.controllers.restControllers;

import com.springframework.petclinictutorial.services.VetService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by sousaJ on 07/09/2020
 * in package - com.springframework.petclinictutorial.controllers.restControllers
 **/
@Controller
@RequestMapping("/vet")
public class RestVetController {

    private final VetService vetService;

    public RestVetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping(value = "/getAllVets", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return ResponseEntity.ok(model.asMap());
    }
}
