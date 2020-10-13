package com.springframework.petclinictutorial.services.map;

import com.springframework.petclinictutorial.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OwnerMapServiceTest {

     OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner owner1 = new Owner();
        owner1.setId(1L);
        ownerMapService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        ownerMapService.save(owner2);
     }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(owners.size(), 2);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(1L);
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(owners.size(), 1);
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByLastName() {
    }
}