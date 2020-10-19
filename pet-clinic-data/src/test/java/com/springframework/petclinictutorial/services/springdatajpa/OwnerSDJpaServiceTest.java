package com.springframework.petclinictutorial.services.springdatajpa;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private final String LAST_NAME = "smith";

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner owner2;
    Owner owner1;
    Owner owner3;

    Set<Owner> owners;

    @BeforeEach
    void setUp() {
         owner1 = new Owner();
         owner1.setId(1L);
         owner1.setLastName(LAST_NAME);

         owner2 = new Owner();
         owner2.setId(2L);

         owner3 = new Owner();
         owner3.setId(3L);

         owners= new HashSet<>();

         owners.add(owner1);
         owners.add(owner2);
         owners.add(owner3);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(Optional.of(owner1));
        Owner smith = service.findByLastName("smith");
        assertEquals("smith", smith.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findAll() {

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> ownersResult = service.findAll();
        assertNotNull(ownersResult);
        assertEquals(3, ownersResult.size());
        assertTrue(ownersResult.contains(owner1));
        assertTrue(ownersResult.contains(owner2));
        assertTrue(ownersResult.contains(owner3));

        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {

        when(ownerRepository.findById(2L)).thenReturn(Optional.of(owner2));

        Owner resultOwner = service.findById(2L);
        assertEquals(2, resultOwner.getId());
        assertEquals(owner2, resultOwner);

        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner resultOwner = service.findById(1L);
        assertNull(resultOwner);

        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void save() {

        when(ownerRepository.save(owner1)).thenReturn(owner1);

        Owner result =  service.save(owner1);
        assertEquals(owner1, result);
        assertEquals(1, result.getId());

        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        service.delete(owner1);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository, times(1)).deleteById(any());
    }
}