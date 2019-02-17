package com.halseyzsmith.sfgpetclinic.bootstrap;

import com.halseyzsmith.sfgpetclinic.model.*;
import com.halseyzsmith.sfgpetclinic.services.OwnerService;
import com.halseyzsmith.sfgpetclinic.services.PetTypeService;
import com.halseyzsmith.sfgpetclinic.services.SpecialtyService;
import com.halseyzsmith.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType savedDogPetType = petTypeService.save(dog);
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded Pet Types...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Westen");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("954-555-1234");


        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");


        owner1.getPets().add(mikesPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("954-555-1234");

        Pet fionasCat = new Pet();
        fionasCat.setPetType(savedCatPetType);
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setName("Fifi");


        owner2.getPets().add(fionasCat);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Specialty radiologySpecialty = new Specialty();
        radiologySpecialty.setDescription("Radiology");
        Specialty savedRadiologySpecialty = specialtyService.save(radiologySpecialty);

        Specialty surgerySpecialty = new Specialty();
        surgerySpecialty.setDescription("Surgery");
        Specialty savedSurgerySpecialty = specialtyService.save(surgerySpecialty);

        Specialty dentistrySpecialty = new Specialty();
        dentistrySpecialty.setDescription("Dentistry");
        Specialty savedDentistrySpecialty = specialtyService.save(dentistrySpecialty);

        System.out.println("Loaded Specialties...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiologySpecialty);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgerySpecialty);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
