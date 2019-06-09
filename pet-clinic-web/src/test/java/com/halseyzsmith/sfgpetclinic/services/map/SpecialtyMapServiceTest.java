package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SpecialtyMapServiceTest {

    private SpecialtyMapService specialtyMapService;

    private final Long ID = 1L;

    @BeforeEach
    void setup() {
        specialtyMapService = new SpecialtyMapService();
        specialtyMapService.save(Specialty.builder().id(ID).build());
    }

    @Test
    void findAllTest() {
        Set<Specialty> specialties = specialtyMapService.findAll();

        assertEquals(1, specialties.size());
    }

    @Test
    void findByIdTest() {
        Specialty specialty = specialtyMapService.findById(ID);

        assertNotNull(specialty);
        assertEquals(ID, specialty.getId());
    }

    @Test
    void saveSpecialtyWithExistingIdTest() {
        Long specialtyId = 2L;
        Specialty specialty = Specialty.builder().id(specialtyId).build();
        Specialty savedSpecialty = specialtyMapService.save(specialty);

        assertEquals(specialtyId, savedSpecialty.getId());
    }

    @Test
    void saveOwnerWithNoIdTest() {
        Specialty specialty = specialtyMapService.save(new Specialty());

        assertNotNull(specialty);
        assertNotNull(specialty.getId());
    }

    @Test
    void deleteTest() {
        Specialty specialty = specialtyMapService.findById(ID);

        assertNotNull(specialty);

        specialtyMapService.delete(specialty);

        assertNull(specialtyMapService.findById(specialty.getId()));
    }

    @Test
    void deleteByIdTest() {
        Specialty specialty = specialtyMapService.findById(ID);

        assertNotNull(specialty);

        specialtyMapService.deleteById(specialty.getId());

        assertNull(specialtyMapService.findById(specialty.getId()));
    }
}
