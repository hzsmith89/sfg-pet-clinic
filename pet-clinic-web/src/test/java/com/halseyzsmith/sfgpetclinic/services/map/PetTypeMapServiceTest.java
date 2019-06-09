package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PetTypeMapServiceTest {

    private PetTypeMapService petTypeMapService;

    private final Long ID = 1L;

    @BeforeEach
    void setup() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(ID).build());
    }

    @Test
    void findAllTest() {
        Set<PetType> petTypes = petTypeMapService.findAll();

        assertEquals(1, petTypes.size());
    }

    @Test
    void findByIdTest() {
        PetType petType = petTypeMapService.findById(ID);

        assertNotNull(petType);
        assertEquals(ID, petType.getId());
    }

    @Test
    void savePetTypeWithExistingIdTest() {
        Long petTypeId = 2L;
        PetType petType = PetType.builder().id(petTypeId).build();
        PetType savedPetType = petTypeMapService.save(petType);

        assertEquals(petTypeId, savedPetType.getId());
    }

    @Test
    void saveOwnerWithNoIdTest() {
        PetType petType = petTypeMapService.save(new PetType());

        assertNotNull(petType);
        assertNotNull(petType.getId());
    }

    @Test
    void deleteTest() {
        PetType petType = petTypeMapService.findById(ID);

        assertNotNull(petType);

        petTypeMapService.delete(petType);

        assertNull(petTypeMapService.findById(petType.getId()));
    }

    @Test
    void deleteByIdTest() {
        PetType petType = petTypeMapService.findById(ID);

        assertNotNull(petType);

        petTypeMapService.deleteById(petType.getId());

        assertNull(petTypeMapService.findById(petType.getId()));
    }
}
