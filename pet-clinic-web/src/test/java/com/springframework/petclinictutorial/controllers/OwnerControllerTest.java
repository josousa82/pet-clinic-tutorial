package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;


    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    Owner owner2;
    Owner owner1;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owner1 = new Owner();
        owner1.setId(1L);

        owner2 = new Owner();
        owner2.setId(2L);

        owners= new HashSet<>();

        owners.add(owner1);
        owners.add(owner2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/owners","/owners/", "/owners/index", "/owners/index.html" })
    void listOwners(String input) throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get(input))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }


    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void getOwnerDetails() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner1);
        mockMvc.perform(get("/owners/1"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processFindFormReturnMany() throws Exception{

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1, owner2));
        mockMvc.perform(get("/owners/owners"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownersList"))
               .andExpect(model().attribute("selections", hasSize(2)));
        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormReturnOne() throws Exception{
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1));
        mockMvc.perform(get("/owners/owners"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));
        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }


}