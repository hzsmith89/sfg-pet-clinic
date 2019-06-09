package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class VetMapServiceTest {

    private VetMapService vetMapService;

    private final Long ID = 1L;

    @BeforeEach
    void setup() {
        vetMapService = new VetMapService(new SpecialtyMapService());
        vetMapService.save(Vet.builder().id(ID).build());
    }

    @Test
    void findAllTest() {
        Set<Vet> vets = vetMapService.findAll();

        assertEquals(1, vets.size());
    }

    @Test
    void findByIdTest() {
        Vet vet = vetMapService.findById(ID);

        assertNotNull(vet);
        assertEquals(ID, vet.getId());
    }

    @Test
    void saveVetWithExistingIdTest() {
        Long vetId = 2L;
        Vet vet = Vet.builder().id(vetId).build();
        Vet savedVet = vetMapService.save(vet);

        assertEquals(vetId, savedVet.getId());
    }

    @Test
    void saveOwnerWithNoIdTest() {
        Vet vet = vetMapService.save(new Vet());

        assertNotNull(vet);
        assertNotNull(vet.getId());
    }

    @Test
    void deleteTest() {
        Vet vet = vetMapService.findById(ID);

        assertNotNull(vet);

        vetMapService.delete(vet);

        assertNull(vetMapService.findById(vet.getId()));
    }

    @Test
    void deleteByIdTest() {
        Vet vet = vetMapService.findById(ID);

        assertNotNull(vet);

        vetMapService.deleteById(vet.getId());

        assertNull(vetMapService.findById(vet.getId()));
    }
}
