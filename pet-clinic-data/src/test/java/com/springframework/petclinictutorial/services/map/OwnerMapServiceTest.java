package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.Pet;
import com.springframework.petclinictutorial.model.PetType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    Owner owner1;
    Owner owner2;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        owner1 = new Owner();
        owner1.setId(1L);
        ownerMapService.save(owner1);

        owner2 = new Owner();
        owner2.setId(2L);
        ownerMapService.save(owner2);
     }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(2, owners.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(1L);
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(owner1);
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void save() {
        Pet pet = new Pet();

        PetType petType = new PetType();
        petType.setName("cat");
        petType.setId(1L);

        pet.setPetType(petType);

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.getPets().add(pet);

        Owner owner = ownerMapService.save(owner3);
        assertTrue(owner.getPets().contains(pet));
        assertEquals(1, owner.getPets().size());

        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(3, owners.size());
        Exception ex = Assertions.assertThrows(InvalidParameterException.class,
                () -> ownerMapService.save(null));
        assertEquals("Owner must not be null", ex.getMessage());
    }
    @Test
    void saveNoId() {
        Owner owner3 = new Owner();
        Owner owner = ownerMapService.save(owner3);
        Assertions.assertNotNull(owner);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);
        assertEquals(1, owner.getId());
    }

    @Test
    void findByLastName() {
        owner1.setFirstName("Jose");
        owner1.setLastName("Sousa");
        Owner owner = ownerMapService.findByLastName("Sousa");
        assertEquals("Sousa", owner.getLastName());
    }
}