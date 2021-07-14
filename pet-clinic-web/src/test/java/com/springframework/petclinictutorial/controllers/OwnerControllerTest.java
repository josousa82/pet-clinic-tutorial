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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    Owner OWNER_2;
    Owner OWNER_1;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        OWNER_1 = new Owner();
        OWNER_1.setId(1L);

        OWNER_2 = new Owner();
        OWNER_2.setId(2L);

        owners= new HashSet<>();

        owners.add(OWNER_1);
        owners.add(OWNER_2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerController)
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/owners/", "/owners/index", "/owners/index.html" })
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
        when(ownerService.findById(anyLong())).thenReturn(OWNER_1);
        mockMvc.perform(get("/owners/1"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processFindFormReturnMany() throws Exception{

        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(OWNER_1, OWNER_2));
        mockMvc.perform(get("/owners/getByLastName"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownersList"))
               .andExpect(model().attribute("selections", hasSize(2)));
        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormReturnOne() throws Exception{
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(List.of(OWNER_1));
        mockMvc.perform(get("/owners/getByLastName"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));
        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }

    @Test
    void processFindFormReturnZeroElements() throws Exception{
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/owners/getByLastName"))
               .andExpect(status().isOk())
               .andExpect(view().name("/owners/findOwners"));
        verify(ownerService, times(1)).findAllByLastNameLike(anyString());
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
               .andExpect(status().isOk())
               .andExpect(view().name("/owners/createOrUpdateOwnerForm"));
    }

    @Test
    void processCreationForm() throws Exception{
        when(ownerService.save(any())).thenReturn(OWNER_1);

        mockMvc.perform(post("/owners/new"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"))
               .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).save(any());
    }

    @Test
    void initOwnerUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(OWNER_1);
        mockMvc.perform(get("/owners/1/edit"))
               .andExpect(status().isOk())
               .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
               .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).findById(any());
    }

    @Test
    void processOwnerUpdateForm() throws Exception {
        when(ownerService.save(any())).thenReturn(OWNER_1);
        mockMvc.perform(post("/owners/1/edit"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"))
               .andExpect(model().attributeExists("owner"));
        verify(ownerService, times(1)).save(any());
    }
}