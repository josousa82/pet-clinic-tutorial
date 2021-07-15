package com.springframework.petclinictutorial.controllers.restApiControllers;

import com.springframework.petclinictutorial.model.Vet;
import com.springframework.petclinictutorial.services.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * Created by sousaJ on 07/09/2020
 * in package - com.springframework.petclinictutorial.controllers.restControllers
 **/
@Controller
@RequestMapping("/api/vet-management")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestVetController {

    private final VetService vetService;

    @GetMapping(value = "/vets")
    public @ResponseBody Set<Vet> getAllVets(){
        return vetService.findAll();
    }
}
