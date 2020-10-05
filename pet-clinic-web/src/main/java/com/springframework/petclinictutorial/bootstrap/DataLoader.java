package com.springframework.petclinictutorial.bootstrap;

import com.springframework.petclinictutorial.model.*;
import com.springframework.petclinictutorial.services.OwnerService;
import com.springframework.petclinictutorial.services.PetTypeService;
import com.springframework.petclinictutorial.services.SpecialityService;
import com.springframework.petclinictutorial.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by sousaJ on 06/09/2020
 * in package - com.springframework.petclinictutorial.bootstrap
 **/

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }


    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);


        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialityService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery = specialityService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry = specialityService.save(dentistry);

        petTypeService.findAll().stream()
                .map(PetType::getName)
                .forEach(System.out::println);

        System.out.println("Loaded PetTypes...");


        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("owner 1 address");
        owner1.setCity("wonderland");
        owner1.setTelephone("1234569");

        Pet mikePet = new Pet();
        mikePet.setName("Rosco");
        mikePet.setPetType(savedDogPetType);
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setOwner(owner1);
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);


        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("owner 2 address");
        owner2.setCity("wonderland escape");
        owner2.setTelephone("123456789");

        Pet fionaCat = new Pet();
        fionaCat.setName("Cat");
        fionaCat.setPetType(savedCatPetType);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setOwner(owner2);
        owner2.getPets().add(fionaCat);
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("owner 3");
        owner3.setLastName(" the 3rd owner  ");
        owner2.setAddress("owner 3 address");
        owner2.setCity("this doesnt live in wonderland");
        owner2.setTelephone("123456789");

        Pet owner3Pet = new Pet();
        owner3Pet.setName("Kitty");
        owner3Pet.setPetType(savedCatPetType);
        owner3Pet.setBirthDate(LocalDate.now());
        owner3Pet.setOwner(owner3);
        owner3.getPets().add(owner3Pet);
        ownerService.save(owner3);


        ownerService.save(owner3);

        ownerService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        System.out.println("Loaded owners...");


        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);



        System.out.println("Loaded vets...");
    }
    public void printMaps(){
        petTypeService.findAll().stream()
                .map(PetType::getName)
                .forEach(System.out::println);

        vetService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);
    }

}
