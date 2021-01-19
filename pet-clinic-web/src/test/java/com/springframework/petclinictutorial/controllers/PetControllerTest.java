package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.controllers.restControllers.PetController;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Created by sousaJ on 19/01/2021
 * in package - com.springframework.petclinictutorial.controllers
 **/
@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Mock
    PetService petServiceMock;

    @Mock
    PetTypeService petTypeServiceMock;

    @Mock
    OwnerService ownerServiceMock;

    @InjectMocks
    PetController petController;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }
}
