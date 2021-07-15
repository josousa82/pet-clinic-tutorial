package com.springframework.petclinictutorial.services.map;


import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.PetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.InvalidParameterException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetMapServiceTest {

    PetMapService petMapService;
    private final Long ID_1 = 1L;
    private final Long ID_2 = 2L;
    private final Pet PET_1 = Pet.builder().id(ID_1).build();
    private final Pet PET_2 = Pet.builder().id(ID_2).build();

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(PET_1);
        petMapService.save(PET_2);
     }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(2, pets.size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(1L);
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void deletePetWithWrongId() {
        var pet = Pet.builder().id(5L).build();
        petMapService.delete(pet);
        Set<Pet> pets = petMapService.findAll();
        assertEquals(2, pets.size());
    }

    @Test
    void deletePetWithNullId() {
        var pet = Pet.builder().build();
        petMapService.delete(pet);
        Set<Pet> pets = petMapService.findAll();
        assertEquals(2, pets.size());
    }

    @Test
    void deleteNull() {
        petMapService.deleteById(null);
        Set<Pet> pets = petMapService.findAll();
        assertEquals(2, pets.size());
    }

    @Test
    void delete() {
        petMapService.delete(PET_1);
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void save() {
        PetType petType = PetType.builder().id(1L).name("cat").build();
        PET_1.setName("Rosco");
        PET_1.setPetType(petType);

        Pet petResult = petMapService.save(PET_1);

        assertEquals(ID_1, petResult.getPetType().getId());
        assertEquals("cat", petResult.getPetType().getName());
        assertEquals(ID_1, petResult.getId());
        assertEquals("Rosco", petResult.getName());


        Exception ex = Assertions.assertThrows(InvalidParameterException.class,
                () -> petMapService.save(null));
        assertEquals("Pet must not be null", ex.getMessage());
    }

    @Test
    void findById() {
        Pet pet= petMapService.findById(ID_1);
        assertEquals(ID_1, pet.getId());
    }

}