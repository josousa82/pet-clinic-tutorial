package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Specialty;
import com.springframework.petclinictutorial.model.Vet;
import com.springframework.petclinictutorial.services.SpecialityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class VetMapServiceTest {

    Vet vet1;
    Vet vet2;
    Vet vet3;
    Set<Specialty> specialties;
    Set<Vet> vets;
    private VetMapService vetMapService;

    @BeforeEach
    void setUp() {
        SpecialityService specialityService = new SpecialityMapService();

        specialties = new HashSet<>();

        Specialty specialty1 = Specialty.builder().id(1L).description("surgery").build();
        specialties.add(specialty1);

        Specialty specialty2 = Specialty.builder().id(2L).description("ortho").build();
        specialties.add(specialty2);

        vetMapService = new VetMapService(specialityService);

        vet1 = Vet.builder().id(1L).lastName("smith").specialty(specialty1).build();

        vetMapService.save(vet1);

        vet2 = Vet.builder().id(2L).specialty(specialty2).build();

        vetMapService.save(vet2);

        vet3 = Vet.builder().id(3L).specialty(specialty2).build();
        vets = vetMapService.findAll();
    }

    @Test
    void findAll() {
        assertEquals(2, vets.size());
    }


    @Test
    void save() {
        Vet vetResult = vetMapService.save(vet3);
        assertEquals(3, vetResult.getId());
        assertEquals(3, vetMapService.findAll().size());
    }

    @Test
    void findById() {
        Vet vetResult = vetMapService.findById(1L);
        assertEquals(1, vetResult.getId());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(1L);
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void delete() {
        vetMapService.delete(vet1);
        assertEquals(1, vetMapService.findAll().size());
        assertTrue(vets.contains(vet2));
    }
}