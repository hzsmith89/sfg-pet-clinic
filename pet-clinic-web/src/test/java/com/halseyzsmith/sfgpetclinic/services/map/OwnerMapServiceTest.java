package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;

    private final Long OWNER_ID = 1L;
    private final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAllTest() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(2, owners.size());
    }

    @Test
    void findByIdTest() {
        Owner owner = ownerMapService.findById(OWNER_ID);

        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void saveOwnerWithExistingIdTest() {
        Long id = 2L;

        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveOwnerWithNoIdTest() {
        Owner owner  = new Owner();
        Owner savedOwner = ownerMapService.save(owner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void deleteTest() {
        Owner owner = ownerMapService.findById(OWNER_ID);

        assertNotNull(owner);

        ownerMapService.delete(owner);

        assertNull(ownerMapService.findById(OWNER_ID));
    }

    @Test
    void deleteByIdTest() {
        Owner owner = ownerMapService.findById(OWNER_ID);

        assertNotNull(owner);

        ownerMapService.deleteById(owner.getId());

        assertNull(ownerMapService.findById(OWNER_ID));
    }


    @Test
    void findByLastNameTest() {
        Owner owner = ownerMapService.findByLastName(LAST_NAME);

        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
        assertEquals(LAST_NAME, owner.getLastName());
    }

    @Test
    void findByLastNameNotFoundTest() {
        Owner owner = ownerMapService.findByLastName("foo");
        assertNull(owner);
    }
}