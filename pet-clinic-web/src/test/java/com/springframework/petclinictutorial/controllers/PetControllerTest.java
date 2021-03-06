package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.PetType;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetService;
import com.springframework.petclinictutorial.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sousaJ on 19/01/2021
 * in package - com.springframework.petclinictutorial.controllers
 **/
@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    void initCreationFormTest() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("owner"))
               .andExpect(model().attributeExists("pet"))
               .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());

        mockMvc.perform(get("/owners/1/pets/2/edit"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("owner"))
               .andExpect(model().attributeExists("pet"))
               .andExpect(model().attributeExists("petId"))
               .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    //TODO refactor tests to reflect the test of post the form with params. Test binding result.
    @Test
    void processUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/2/edit"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));

        verify(petService).update(any(), any());
    }

//    @Test
//    void populatePetTypesTest(){
//        //TODO implement
//    }
//    @Test
//    void findOwnerTest(){
//        //TODO implement
//    }
//    @Test
//    void initOwnerBinderTest(){
//        //TODO implement
//    }
}
