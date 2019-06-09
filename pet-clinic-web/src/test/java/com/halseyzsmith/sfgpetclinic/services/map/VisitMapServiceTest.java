package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import com.halseyzsmith.sfgpetclinic.model.Pet;
import com.halseyzsmith.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class VisitMapServiceTest {

    private VisitMapService visitMapService;

    private final Long ID = 1L;

    private Pet pet;

    @BeforeEach
    void setup() {
        Owner owner = Owner.builder().id(ID).build();
        pet =  Pet.builder().id(ID).owner(owner).build();

        visitMapService = new VisitMapService();
        visitMapService.save(Visit.builder().id(ID).pet(pet).build());
    }

    @Test
    void findAllTest() {
        Set<Visit> visits = visitMapService.findAll();

        assertEquals(1, visits.size());
    }

    @Test
    void findByIdTest() {
        Visit visit = visitMapService.findById(ID);

        assertNotNull(visit);
        assertEquals(ID, visit.getId());
    }

    @Test
    void saveVisitWithExistingIdTest() {
        Long visitId = 2L;
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        Visit savedVisit = visitMapService.save(visit);

        assertEquals(visitId, savedVisit.getId());
    }

    @Test
    void saveOwnerWithNoIdTest() {
        Long visitId = 2L;
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        Visit savedVisit = visitMapService.save(visit);

        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
    }

    @Test
    void deleteTest() {
        Visit visit = visitMapService.findById(ID);

        assertNotNull(visit);

        visitMapService.delete(visit);

        assertNull(visitMapService.findById(visit.getId()));
    }

    @Test
    void deleteByIdTest() {
        Visit visit = visitMapService.findById(ID);

        assertNotNull(visit);

        visitMapService.deleteById(visit.getId());

        assertNull(visitMapService.findById(visit.getId()));
    }
}
