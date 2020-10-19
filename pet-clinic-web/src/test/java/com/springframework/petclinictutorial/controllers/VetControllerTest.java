package com.springframework.petclinictutorial.controllers;

import com.springframework.petclinictutorial.model.Vet;
import com.springframework.petclinictutorial.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    Vet vet1;
    Vet vet2;

    Set<Vet> vets;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vet1 = new Vet();
        vet1.setId(1L);

        vet2 = new Vet();
        vet2.setId(2L);

        vets = new HashSet<>();

        vets.add(vet1);
        vets.add(vet2);

        vets = new HashSet<>();
        vets.add(vet1);
        vets.add(vet2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(vetController)
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"/vets","/vets/index", "/vets/index.html", "/vets.html"})
    void listVets(String input) throws Exception {
        when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(get(input))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }
}