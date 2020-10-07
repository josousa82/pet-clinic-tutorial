package com.springframework.petclinictutorial.controllers.restControllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * Created by sousaJ on 06/09/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@RestController
@RequestMapping("/restOwners")
public class RestOwnerController {

    private final OwnerService ownerService;

    public RestOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/getOwners", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Owner> getOwnersList(){
        return ownerService.findAll();
    }
}
