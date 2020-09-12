package com.springframework.petclinictutorial.bootstrap;

import com.springframework.petclinictutorial.model.Owner;
import com.springframework.petclinictutorial.model.Person;
import com.springframework.petclinictutorial.model.PetType;
import com.springframework.petclinictutorial.model.Vet;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetTypeService;
import com.springframework.petclinictutorial.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by sousaJ on 06/09/2020
 * in package - com.springframework.petclinictutorial.bootstrap
 **/

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;


    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setType("Dog");
        PetType savedDogPetType = petTypeService.save(dog);


        PetType cat = new PetType();
        cat.setType("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        petTypeService.findAll().stream()
                .map(PetType::getType)
                .forEach(System.out::println);

        System.out.println("Loaded PetTypes...");


        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);


        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("tes");
        owner3.setLastName("g");


        ownerService.save(owner3);

        ownerService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        System.out.println("Loaded owners...");


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        vetService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        System.out.println("Loaded vets...");


    }

}
