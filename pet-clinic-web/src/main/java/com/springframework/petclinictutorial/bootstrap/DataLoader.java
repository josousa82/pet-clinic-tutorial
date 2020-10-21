package com.springframework.petclinictutorial.bootstrap;

import com.springframework.petclinictutorial.model.*;
import com.springframework.petclinictutorial.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by sousaJ on 06/09/2020
 * in package - com.springframework.petclinictutorial.bootstrap
 **/

@Component
@Profile({"springdatajpa", "default", "map"})
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, VisitService visitService, SpecialityService specialityService) {
        log.error("boostrap class constructor");
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
        this.specialityService = specialityService;
    }


    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();
        if(count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = PetType.builder().id(1L).name("Dog").build();
        PetType cat = PetType.builder().id(2L).name("Cat").build();

        PetType savedDogPetType = petTypeService.save(dog);
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded Pet...");

        Specialty radiology = Specialty.builder().id(1L).description("Radiology").build();
        Specialty surgery = Specialty.builder().id(2L).description("Surgery").build();

        Specialty savedRadiology = specialityService.save(radiology);
        Specialty savedSurgery = specialityService.save(surgery);

        System.out.println("Loaded PetTypes...");


        Pet mikePet = new Pet();
        mikePet.setId(1L);
        mikePet.setName("Rosco");
        mikePet.setPetType(savedDogPetType);
        mikePet.setBirthDate(LocalDate.now());


        Owner owner1 = Owner.builder().id(1L).firstName("Michael").lastName("Weston")
                .address("owner 1 address").city("wonderland").telephone("1234569").build();

        mikePet.setOwner(owner1);

        ownerService.save(owner1);
//        owner1.getPets().add(mikePet);

//        Owner owner2 = new Owner();
//        owner2.setId(2L);
//        owner2.setFirstName("Fiona");
//        owner2.setLastName("Glenanne");
//        owner2.setAddress("owner 2 address");
//        owner2.setCity("wonderland escape");
//        owner2.setTelephone("123456789");
//
//        Pet fionaCat = new Pet();
//        fionaCat.setName("Cat");
//        fionaCat.setPetType(savedCatPetType);
//        fionaCat.setBirthDate(LocalDate.now());
//        fionaCat.setOwner(owner2);
//        owner2.getPets().add(fionaCat);
//        ownerService.save(owner2);
//
//        Owner owner3 = new Owner();
//        owner3.setId(3L);
//        owner3.setFirstName("owner 3");
//        owner3.setLastName(" the 3rd owner  ");
//        owner2.setAddress("owner 3 address");
//        owner2.setCity("this doesnt live in wonderland");
//        owner2.setTelephone("123456789");
//
//        Pet owner3Pet = new Pet();
//        owner3Pet.setName("Kitty");
//        owner3Pet.setPetType(savedCatPetType);
//        owner3Pet.setBirthDate(LocalDate.now());
//        owner3Pet.setOwner(owner3);
//        owner3.getPets().add(owner3Pet);
//        ownerService.save(owner3);
//
//        Visit catVisit  = new Visit();
//        catVisit.setPet(owner3Pet);
//        catVisit.setDate(LocalDate.now());
//        catVisit.setDescription("kitty sneezing");
//        catVisit.setId(owner3Pet.getId());
////        visitService.save(catVisit);


        System.out.println("Loaded owners...");

        Vet vet1 = Vet.builder()
                .id(1L)
                .firstName("Sam")
                .lastName("Axe")
                .specialty(savedRadiology)
                .build();
//        vet1.getSpecialties().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = Vet.builder()
                .id(2L)
                .firstName("Jessie")
                .lastName("Porter")
                .specialty(savedSurgery)
                .build();
//        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);



        System.out.println("Loaded vets...");

        printMaps();
    }
    public void printMaps(){
        petTypeService.findAll().stream()
                .map(PetType::getName)
                .forEach(System.out::println);

        vetService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        ownerService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        petTypeService.findAll().stream()
                .map(PetType::getName)
                .forEach(System.out::println);

        vetService.findAll().stream()
                .map(Vet::getFirstName)
                .forEach(System.out::println);

    }

}
