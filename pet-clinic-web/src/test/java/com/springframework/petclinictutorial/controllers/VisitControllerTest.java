package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.Visit;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sousaJ on 25/01/2021
 * in package - com.springframework.petclinictutorial.controllers
 **/
@ExtendWith(MockitoExtension.class)
 class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    Visit visit;

    Pet pet;

    @BeforeEach
     void setUp() {

        visit = Visit.builder().id(1L).build();
        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
        pet = Pet.builder().id(1L).build();
    }

    @Test
    void initNewVisitFormTest() throws Exception {

        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/*/pets/1/visits/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("visits/createOrUpdateVisitForm"))
               .andExpect(model().attribute("pet", hasProperty("id", is(1L))))
               .andExpect(model().attribute("pet", hasProperty("visits", hasSize(1))));
        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void processNewVisitFormTest() throws Exception {

        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"))
               .andExpect(model().attribute("pet", hasProperty("visits", hasSize(1))));

        verify(visitService, times(1)).addNewVisit(any(), any());
    }

    //TODO implement test for binding result with errors

}
