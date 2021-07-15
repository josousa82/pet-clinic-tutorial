package com.springframework.petclinictutorial.controllers.restApiControllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by sousaJ on 06/09/2020
 * in package - com.springframework.petclinictutorial.controllers
 **/
@RestController
@RequestMapping("/restOwners")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestOwnerController {

    private final OwnerService ownerService;

    @ApiOperation(value = "Get all owners")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "200 - Success Get all owners", response = Owner.class)})

    @GetMapping(value = "/getOwners")
    public List<Owner> getOwnersList(){
        List<Owner> result = new ArrayList<>();
        Iterator<Owner> owners = ownerService.findAll().iterator();
        while (owners.hasNext()){
            result.add(owners.next());
        }
        return result;
    }
}
