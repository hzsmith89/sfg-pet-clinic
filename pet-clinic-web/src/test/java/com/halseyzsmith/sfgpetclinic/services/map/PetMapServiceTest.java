package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PetMapServiceTest {

    private PetMapService petMapService;

    private final Long ID = 1L;

    @BeforeEach
    void setup() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(ID).build());
    }

    @Test
    void findAllTest() {
        Set<Pet> pets = petMapService.findAll();

        assertEquals(1, pets.size());
    }

    @Test
    void findByIdTest() {
        Pet pet = petMapService.findById(ID);

        assertNotNull(pet);
        assertEquals(ID, pet.getId());
    }

    @Test
    void savePetWithExistingIdTest() {
        Long petId = 2L;
        Pet pet = Pet.builder().id(petId).build();
        Pet savedPet = petMapService.save(pet);

        assertEquals(petId, savedPet.getId());
    }

    @Test
    void saveOwnerWithNoIdTest() {
        Pet pet = petMapService.save(new Pet());

        assertNotNull(pet);
        assertNotNull(pet.getId());
    }

    @Test
    void deleteTest() {
        Pet pet = petMapService.findById(ID);

        assertNotNull(pet);

        petMapService.delete(pet);

        assertNull(petMapService.findById(pet.getId()));
    }

    @Test
    void deleteByIdTest() {
        Pet pet = petMapService.findById(ID);

        assertNotNull(pet);

        petMapService.deleteById(pet.getId());

        assertNull(petMapService.findById(pet.getId()));
    }
}
