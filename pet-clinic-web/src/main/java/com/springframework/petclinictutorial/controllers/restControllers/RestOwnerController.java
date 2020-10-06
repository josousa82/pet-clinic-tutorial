package com.springframework.petclinictutorial.controllers.restControllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

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
    public ResponseEntity<Set<Owner>> getOwnersList() throws NullPointerException{
        return ResponseEntity.ok( ownerService.findAll());
    }
}
