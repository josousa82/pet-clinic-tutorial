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

@Slf4j
@Component
@Profile({"springdatajpa", "default", "map"})
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, VisitService visitService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();
        if(count == 0)
            loadData();
    }

    private void loadData() {

        log.info("-------------> BOOTSTRAP DATA LOADING <-------------");

        PetType dog = PetType.builder().name("Dog").build();
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        PetType savedCatPetType = petTypeService.save(cat);

        log.warn("-------------> Loaded PetTypes...");

        Specialty radiology = Specialty.builder().description("Radiology").build();
        Specialty savedRadiology = specialityService.save(radiology);

        Specialty surgery = Specialty.builder().description("Surgery").build();
        Specialty savedSurgery = specialityService.save(surgery);

        Specialty dentistry = Specialty.builder().description("Dentistry").build();
        Specialty savedDentistry = specialityService.save(dentistry);

        log.warn("-------------> Loaded Specialities...");

        Pet mikePet = Pet.builder().name("Rosco").petType(savedDogPetType).birthDate(LocalDate.now()).build();

        Owner ownerMike = Owner.builder().firstName("Michael").lastName("Weston")
                .address("owner 1 address").city("wonderland").telephone("1234569").pet(mikePet).build();

        mikePet.setOwner(ownerMike);
        ownerService.save(ownerMike);

        log.warn("-------------> Saved Mike");

        Pet fionaCat =Pet.builder().name("Cat").petType(savedCatPetType).birthDate(LocalDate.now()).build();
        Owner fionaOwner = Owner.builder().firstName("Fiona").lastName("Glenanne")
                .address("owner 2 address").city("wonderland escape").telephone("123456789").pet(fionaCat).build();

        fionaCat.setOwner(fionaOwner);
        ownerService.save(fionaOwner);

        log.warn("-------------> Saved Fiona");

        Pet josePet = Pet.builder().name("pipa").petType(savedCatPetType)
                .birthDate(LocalDate.now()).build();

        Owner joseOwner = Owner.builder().firstName("jose").lastName("rei")
                .address("jose candy house").city("wonderland").telephone("123456789").pet(josePet).build();

        ownerService.save(joseOwner);

        log.warn("-------------> Saved Jose");

        Visit fionaCatVisit  = Visit.builder().date(LocalDate.now()).description("kitty sneezing").pet(fionaCat).build();
        visitService.save(fionaCatVisit);

        log.warn("-------------> Saved fionaCatVisit");

        Visit joseCatVisit  = Visit.builder().date(LocalDate.now()).description("kitty sneezing").pet(josePet).build();
        visitService.save(joseCatVisit);

        log.warn("-------------> Saved joseCatVisit");

        Visit mikeDogVisit  = Visit.builder().date(LocalDate.now()).description("Dog sneezing").pet(mikePet).build();
        visitService.save(mikeDogVisit);

        log.warn("-------------> Saved mikeDogVisit");

        log.warn("-------------> Loading vets");

        Vet vetAxe = Vet.builder().id(1L).firstName("Sam").lastName("Axe").specialty(savedRadiology).build();
        vetService.save(vetAxe);
        log.warn("-------------> Loaded vetAxe");

        Vet vetJessie = Vet.builder().id(2L).firstName("Jessie").lastName("Porter").specialty(savedSurgery).build();

        vetAxe.addSpeciality(dentistry);
        log.warn("-------------> Loaded vetJessie dentistry speciality");

        vetService.save(vetJessie);

        log.warn("-------------> Loaded vetJessie");


        log.info("-------------> DATA LOADED SUCCESSFULLY <-------------");

        printMaps();
    }

    public void printMaps(){

        log.info("-------------> PETS LOADED");
        petTypeService.findAll().stream()
                .map(PetType::getName)
                .forEach(System.out::println);

        log.info("-------------> Vets loaded");
        vetService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        log.info("-------------> Owners loaded");
        ownerService.findAll().stream()
                .map(Person::getFirstName)
                .forEach(System.out::println);

        log.info("-------------> PetTypes loaded");
        petTypeService.findAll().stream()
                .map(PetType::getName)
                .forEach(System.out::println);

    }

}
