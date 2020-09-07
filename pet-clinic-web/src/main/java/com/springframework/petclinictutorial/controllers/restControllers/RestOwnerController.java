package com.springframework.petclinictutorial.controllers.restControllers;

import com.springframework.petclinictutorial.services.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * Created by sousaJ on 06/09/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@Controller
@RequestMapping("/restOwners")
public class RestOwnerController {

    private final OwnerService ownerService;

    public RestOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/getOwners", method = GET)
    public ResponseEntity<Map<String, Object>> getOwnersList(Model model) throws NullPointerException{
        model.addAttribute("ownersList",ownerService.findAll() );
        return ResponseEntity.ok(model.asMap());
    }
}
